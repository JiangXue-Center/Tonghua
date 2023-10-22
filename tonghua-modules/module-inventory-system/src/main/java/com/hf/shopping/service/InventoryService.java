package com.hf.shopping.service;

import com.hf.shopping.model.vo.SpuIndexVO;

import java.util.List;

public interface InventoryService {
    List<SpuIndexVO> selectSpuIndexVoByKeyword(String keyword);

    void selectSpuDetailById(Long productId);
}
