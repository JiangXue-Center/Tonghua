package com.hf.inventory.utils;

import com.hf.core.model.entity.inventory.*;
import com.hf.inventory.model.vo.BusinessVO;
import com.hf.inventory.model.vo.SkuBaseInfo;
import com.hf.inventory.model.vo.SpuBaseInfo;
import com.hf.inventory.model.vo.SpuDetailVO;

import java.util.*;
import java.util.stream.Collectors;

public class SpuDetailUtil {

    /**
     * 根据参数id获取参数名
     * @param id 指定参数的id
     * @param params 参数集合
     * @return 返回参数名
     */
    public static String getParamName(String id, List<SpecParam> params) {
        String name = "";
        for (SpecParam param : params) {
            String specParamId = String.valueOf(param.getSpecParamId());
            if (specParamId.equals(id)) {
                name = param.getName();
                break;
            }
        }
        return name;
    }

    /**
     * 拼接一个Spu详情VO类的对象
     * @param spuDetail Spu详情的原石信息
     * @param smap 特殊规格参数的map
     * @param gmap 通用规格参数的map
     * @return 返回Spu详情VO
     */
    public static SpuDetailVO createSpuDetailVo(
            SpuDetail spuDetail,
            LinkedHashMap<String, List<String>> smap,
            Map<String, Map<String, String>> gmap) {

        //1.设置商品详情信息
        SpuDetailVO spuDetailVo = new SpuDetailVO();
        spuDetailVo.setDescription(spuDetail.getDescription());
        spuDetailVo.setAfterService(spuDetail.getAfterService());
        spuDetailVo.setSpecialSpec(smap);
        spuDetailVo.setGenericSpec(gmap);
        return spuDetailVo;
    }


    /**
     * 创建一个K为参数名，V为参数值的map
     * @param smap K为参数id，V为参数值集合的map
     * @param params Spu所有参数的集合
     * @return 返回一个只包含特殊规格参数且K为参数名，V为参数值列表的map
     */
    public static LinkedHashMap<String, List<String>> createSpecialMap(
            LinkedHashMap<String, List<String>> smap,
            List<SpecParam> params) {
        return smap.entrySet().stream()
                .collect(Collectors.toMap(
                        //指定了新map的键
                        entry -> SpuDetailUtil.getParamName(entry.getKey(), params),
                        //指定新map的值
                        Map.Entry::getValue,
                        //指定键冲突是的合并方案
                        (v1, v2) -> v1,
                        //指定新map的类型
                        LinkedHashMap::new
                ));
    }

    /**
     * 创建一个K为参数组名称，V为参数组下的参数map的map
     * @param groups 参数组列表
     * @param gmap k为参数id，v为参数值的map
     * @param params Spu所有参数的集合
     * @return 返回一个只包含通用规格参数的K为参数组名称，V为参数组下的参数map的map
     */
    public static Map<String, Map<String, String>> createGenericMap(
            List<SpecGroup> groups,
            LinkedHashMap<String, String> gmap,
            List<SpecParam> params) {

//        Map<String, Map<String, String>> map = new HashMap<>();
//        List<SpecParam> genericParams = params.stream().filter(SpecParam::isGeneric).toList();
//        for (SpecGroup group : groups) {
//            long groupId = group.getSpecGroupId();
//            String name = group.getName();
//            Map<String, String> temp = new HashMap<>();
//            for (SpecParam genericParam: genericParams) {
//                if (genericParam.getGroupId() == groupId) {
//                    String paramId = String.valueOf(genericParam.getSpecParamId());
//                    temp.put(genericParam.getName(), gmap.get(paramId));
//                }
//            }
//            map.put(name, temp);
//        }

        return groups.stream()
                .collect(Collectors.toMap(
                        //指定新map的K为参数组名
                        SpecGroup::getName,
                        group -> params.stream()
                                //过滤掉参数组id不等于当前参数组id且非通用参数的元素
                                .filter(param -> param.getGroupId() == group.getSpecGroupId() && param.isGeneric())
                                .collect(Collectors.toMap(
                                        //指定新map的K为参数名
                                        SpecParam::getName,
                                        //指定新map的V为参数值
                                        param -> gmap.get(String.valueOf(param.getSpecParamId()))
                                ))
                ));
    }

    /**
     * 获取参数组的id
     * @param params Spu的所有参数列表
     * @return 返回一个只包含Spu的所有参数的参数组的id列表
     */
    public static List<Long> getGroupIds(List<SpecParam> params) {
        return params.stream()
                //过滤掉非通用的参数
                .filter(SpecParam::isGeneric)
                //将元素映射成参数组id
                .map(SpecParam::getGroupId)
                //去重
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 拼装一个Spu详情页所需要的信息的map
     * @param spu 原始的Spu信息
     * @param spuDetailVo 已经拼装好的Spu详情VO类
     * @param business 商家信息
     * @param skuBaseInfos Sku的基本信息
     * @return 返回一个拼装好的Spu详情页所需要的信息的map
     */
    public static Map<String, Object> createSpuDetailMap(
            StandardProductUnit spu,
            SpuDetailVO spuDetailVo,
            Business business,
            List<SkuBaseInfo> skuBaseInfos) {
        Map<String, Object> result = new HashMap<>();
        BusinessVO businessVO = createBusinessVO(business);
        SpuBaseInfo spuBaseInfo = createSpuBaseInfo(spu);
        result.put("spuBaseInfo", spuBaseInfo);
        result.put("skuBaseInfos", skuBaseInfos);
        result.put("detail", spuDetailVo);
        result.put("business", businessVO);

        return result;
    }

    /**
     * 创建一个详情页中的商家VO
     * @param business 原始的商家信息
     * @return 返回一个详情页中的商家VO
     */
    public static BusinessVO createBusinessVO(Business business) {
        BusinessVO businessVO = new BusinessVO();
        businessVO.setBusinessId(business.getBusinessId());
        businessVO.setBusinessName(business.getBusinessName());
        businessVO.setLogo(business.getBusinessLogo());
        return businessVO;
    }

    /**
     * 创建一个Spu详情页中的基本信息
     * @param spu 原始的Spu信息
     * @return 返回一个处理好的Spu详情页中的的基本信息
     */
    public static SpuBaseInfo createSpuBaseInfo(StandardProductUnit spu) {
        SpuBaseInfo spuBaseInfo = new SpuBaseInfo();
        spuBaseInfo.setSpuId(spu.getSpuId());
        spuBaseInfo.setTitle(spu.getSubTitle());
        return spuBaseInfo;
    }


}
