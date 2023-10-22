package com.hf.inventory.utils;

import com.hf.core.model.entity.inventory.SpecGroup;
import com.hf.core.model.entity.inventory.SpecParam;
import com.hf.core.model.entity.inventory.SpuDetail;
import com.hf.inventory.model.vo.SpuDetailVo;

import java.util.*;
import java.util.stream.Collectors;

public class SpuDetailUtil {

    public static String getParamName(String id, List<SpecParam> list) {
        String name = "";
        for (SpecParam param : list) {
            String specParamId = String.valueOf(param.getSpecParamId());
            if (specParamId.equals(id)) {
                name = param.getName();
            }
        }
        return name;
    }

    public static SpuDetailVo createSpuDetailVo(
            SpuDetail spuDetail,
            LinkedHashMap<String, List<String>> smap,
            Map<String, Map<String, String>> gmap) {

        SpuDetailVo spuDetailVo = new SpuDetailVo();
        spuDetailVo.setSpuId(spuDetail.getSpuId());
        spuDetailVo.setDescription(spuDetail.getDescription());
        spuDetailVo.setAfterService(spuDetail.getAfterService());
        spuDetailVo.setSpuDetailId(spuDetail.getSpuDetailId());
        spuDetailVo.setSpecialSpec(smap);
        spuDetailVo.setGenericSpec(gmap);
        return spuDetailVo;
    }

    public static LinkedHashMap<String, List<String>> createSpecialMap(
            LinkedHashMap<String, List<String>> smap,
            List<SpecParam> params) {
        return smap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> SpuDetailUtil.getParamName(entry.getKey(), params),
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

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
                        SpecGroup::getName,
                        group -> params.stream()
                                .filter(param -> param.getGroupId() == group.getSpecGroupId() && param.isGeneric())
                                .collect(Collectors.toMap(
                                        SpecParam::getName,
                                        param -> gmap.get(String.valueOf(param.getSpecParamId()))
                                ))
                ));
    }

    public static List<Long> getGroupIds(List<SpecParam> params) {
        return params.stream()
                .filter(SpecParam::isGeneric)
                .map(SpecParam::getGroupId)
                .distinct()
                .collect(Collectors.toList());
    }


}
