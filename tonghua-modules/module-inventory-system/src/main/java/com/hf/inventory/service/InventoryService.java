package com.hf.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hf.inventory.model.vo.SpuIndexVO;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    List<SpuIndexVO> selectSpuIndexVOByKeyword(String keyword);

    Map<String, Object> selectSpuDetailById(Long productId) throws JsonProcessingException;

    List<SpuIndexVO> recommandeSpuIndexPage(String id, Integer offset, Integer size);
}
