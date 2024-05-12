package com.hf.inventory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hf.cache.service.RedisLock;
import com.hf.cache.service.RedisService;
import com.hf.core.exception.ParamException;
import com.hf.core.model.entity.inventory.*;
import com.hf.core.model.entity.order.Order;
import com.hf.core.model.entity.order.OrderDetail;
import com.hf.inventory.mapper.BusinessMapper;
import com.hf.inventory.mapper.SpuDetailMapper;
import com.hf.inventory.model.vo.SkuBaseInfo;
import com.hf.inventory.model.vo.SpuDetailVO;
import com.hf.inventory.utils.SpuDetailUtil;
import com.hf.minio.service.MinIOService;
import com.hf.inventory.mapper.SkuMapper;
import com.hf.inventory.mapper.SpuMapper;
import com.hf.inventory.model.vo.SpuIndexVO;
import com.hf.inventory.service.InventoryService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hf.amqp.constants.MQConstants.ORDER_INVENTORY_QUEUE;
import static com.hf.inventory.constants.RedisConstants.*;
import static com.hf.minio.constant.MinIOConstant.*;

@Service
public class InventoryServiceImpl implements InventoryService {
    
    @Autowired
    private SpuMapper spuMapper;
    
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private BusinessMapper businessMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MinIOService minIOService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedissonClient redissonClient;
    
    @Override
    public List<SpuIndexVO> selectSpuIndexVOByKeyword(String keyword) {
        List<SpuIndexVO> spuIndexVOS = spuMapper.selectSpuIndexByKeyword(keyword);
        for (SpuIndexVO spuIndexVO : spuIndexVOS) {
            if (!StrUtil.hasBlank(spuIndexVO.getMainImage())) {
                String link = minIOService.path2Link(spuIndexVO.getMainImage(), SPU_BUCKET_NAME, SKU_FOLDER);
                spuIndexVO.setMainImage(link);
            }
        }
        return spuIndexVOS;
    }

    @Override
    public Map<String, Object> selectSpuDetailById(Long productId) throws JsonProcessingException {
        //1.先根据spu_id查询出spu对象
        StandardProductUnit spu = spuMapper.selectSpuById(productId);
        if (Optional.ofNullable(spu).isEmpty()) {
            return new HashMap<>();
        }
        //2.根据spu_id查询出Spu的详情信息
        SpuDetail spuDetail = spuDetailMapper.selectSpuDetailBySpuId(productId);
        //3.获取通用规格与特殊规格
        String genericSpec = spuDetail.getGenericSpec();
        String specialSpec = spuDetail.getSpecialSpec();
        //4.若空则空
        LinkedHashMap<String, List<String>> smap =
                StrUtil.isBlank(specialSpec)
                        ? new LinkedHashMap<>()
                        : objectMapper.readValue(specialSpec, LinkedHashMap.class);
        LinkedHashMap<String, String> gmap =
                StrUtil.isBlank(genericSpec)
                        ? new LinkedHashMap<>()
                        : objectMapper.readValue(genericSpec, LinkedHashMap.class);
        //5.通过查询出来的参数组合成一个参数id集合，然后再通过这个id结合查询商品所有参数的信息
        List<SpecParam> params = spuDetailMapper.selectSpecParam(
                Stream.concat(smap.keySet().stream(), gmap.keySet().stream())
                        .collect(Collectors.toSet()));
        //6.查询所有参数组的信息
        List<Long> groupIds = SpuDetailUtil.getGroupIds(params);
        if (groupIds.isEmpty()) {
            return new HashMap<>();
        }
        List<SpecGroup> groups = spuDetailMapper.selectSpecGroup(groupIds);
        //7.拼装成商品详情的对象
        SpuDetailVO spuDetailVo = SpuDetailUtil.createSpuDetailVo(
                spuDetail, SpuDetailUtil.createSpecialMap(smap, params),
                SpuDetailUtil.createGenericMap(groups, gmap, params));
        //8.查询出默认的SKU基本信息
        List<SkuBaseInfo> skuBaseInfos = skuMapper.selectSkuBaseInfoBySpuId(spu.getSpuId());
        for (SkuBaseInfo skuBaseInfo : skuBaseInfos) {
            String link = minIOService.path2Link(skuBaseInfo.getImage(), SPU_BUCKET_NAME, SKU_FOLDER);
            skuBaseInfo.setImage(link);
        }
        //9.查询出商品相关店铺的信息
        Business business = businessMapper.selectBusinessById(spu.getBusinessId());
        //10.将店铺logo图片以及商品主图的路径转换成在线链接
        String logoLink = minIOService.path2Link(business.getBusinessLogo(), SHOP_BUCKET_NAME, SHOP_LOGO_FOLDER);
        String mainImageLink = minIOService.path2Link(spu.getMainImage(), SPU_BUCKET_NAME, SKU_FOLDER);
        spu.setMainImage(mainImageLink);
        business.setBusinessLogo(logoLink);
        //11.拼装成一个map对象作为响应体
        return SpuDetailUtil.createSpuDetailMap(spu, spuDetailVo, business, skuBaseInfos);
    }

    @Override
    public List<SpuIndexVO> recommandeSpuIndexPage(String id, Integer offset, Integer size) {
        if (offset == null || size == null) {
            throw new RuntimeException();
        }
        if (offset < 1) {
            throw new RuntimeException();
        }
        List<SpuIndexVO> spuIndexVOS = spuMapper.recommendSpuIndex(offset - 1, size);
        spuIndexVOS = spuIndexVOS.stream().map(spuIndexVO -> {
            spuIndexVO.setMainImage(minIOService.path2Link(spuIndexVO.getMainImage(), SPU_BUCKET_NAME, SKU_FOLDER));
            return spuIndexVO;
        }).collect(Collectors.toList());
        return spuIndexVOS;
    }

    @Override
    public boolean updateStock(Long skuId, Integer num) {
        if (skuId == null || skuId < 0) {
            throw new ParamException();
        }
        String key = LOCK_STOCK_KEY + skuId;
        RLock lock = RedisLock.getLock(redissonClient, key);
        Integer result = 0;
        try {
            // 尝试加锁，最多等待100秒，锁的过期时间设置为30秒
            boolean isLocked = lock.tryLock(LOCK_MAX_WAIT_TIME, LOCK_EXPIRE_TTL, java.util.concurrent.TimeUnit.SECONDS);

            if (isLocked) {
                // 执行需要加锁的业务逻辑
                result = skuMapper.updateStockBySkuId(skuId, num);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 释放锁
            lock.unlock();
        }
        return result > 0;
    }

    @RabbitListener(queues = ORDER_INVENTORY_QUEUE)
    public void processSubmitOrder(Map<String , Object> map) {
        Order order = (Order) map.get("order");
        System.out.println(order);
        List<OrderDetail> orderDetails = (List<OrderDetail>) map.get("orderDetails");
        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail);
            long skuId = orderDetail.getSkuId();
            String key = PRODUCT_INVENTORY_KEY + skuId;
            int purchaseQuantity = orderDetail.getPurchaseQuantity();
            Integer stock = redisService.getCacheObject(key);
            if (stock == null) {
                stock = skuMapper.selectStockBySkuId(skuId);
            }
            if (stock == 0 || stock < purchaseQuantity) {
                System.out.println("库存不足");
                throw new RuntimeException();
            } else {
                redisService.setCacheObject(key, stock - purchaseQuantity, PRODUCT_INVENTORY_TTL, TimeUnit.MINUTES);
            }
        }
    }

}
