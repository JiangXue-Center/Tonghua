package com.hf.inventory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hf.core.model.entity.inventory.SpecGroup;
import com.hf.core.model.entity.inventory.SpecParam;
import com.hf.core.model.entity.inventory.SpuDetail;
import com.hf.core.model.entity.inventory.StandardProductUnit;
import com.hf.inventory.mapper.SpuDetailMapper;
import com.hf.inventory.model.vo.SpuDetailVo;
import com.hf.inventory.utils.SpuDetailUtil;
import com.hf.minio.service.MinIOService;
import com.hf.inventory.mapper.SkuMapper;
import com.hf.inventory.mapper.SpuMapper;
import com.hf.inventory.model.vo.SpuIndexVO;
import com.hf.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hf.minio.constant.MinIOConstant.SKU_FOLDER;
import static com.hf.minio.constant.MinIOConstant.SPU_BUCKET_NAME;

@Service
public class InventoryServiceImpl implements InventoryService {
    
    @Autowired
    private SpuMapper spuMapper;
    
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MinIOService minIOService;
    
    @Override
    public List<SpuIndexVO> selectSpuIndexVoByKeyword(String keyword) {
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
        StandardProductUnit spu = spuMapper.selectSpuById(productId);
        if (Optional.ofNullable(spu).isEmpty()) {
            return new HashMap<>();
        }

        SpuDetail spuDetail = spuDetailMapper.selectSpuDetailBySpuId(productId);
        String genericSpec = spuDetail.getGenericSpec();
        String specialSpec = spuDetail.getSpecialSpec();
        LinkedHashMap<String, List<String>> smap =
                StrUtil.isBlank(specialSpec)
                        ? new LinkedHashMap<>()
                        : objectMapper.readValue(specialSpec, LinkedHashMap.class);
        LinkedHashMap<String, String> gmap =
                StrUtil.isBlank(genericSpec)
                        ? new LinkedHashMap<>()
                        : objectMapper.readValue(genericSpec, LinkedHashMap.class);

        List<SpecParam> params = spuDetailMapper.selectSpecParam(
                Stream.concat(smap.keySet().stream(), gmap.keySet().stream())
                        .collect(Collectors.toSet()));
        List<SpecGroup> groups = spuDetailMapper.selectSpecGroup(SpuDetailUtil.getGroupIds(params));
        SpuDetailVo spuDetailVo = SpuDetailUtil.createSpuDetailVo(
                spuDetail, SpuDetailUtil.createSpecialMap(smap, params),
                SpuDetailUtil.createGenericMap(groups, gmap, params));

        Map<String, Object> result = new HashMap<>();
        result.put("detail", spuDetailVo);
        return result;
    }




}
