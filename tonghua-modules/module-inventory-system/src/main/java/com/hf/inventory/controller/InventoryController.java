package com.hf.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hf.core.model.Result;
import com.hf.inventory.model.vo.SpuIndexVO;
import com.hf.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sh")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

//    @GetMapping("keyword/{keyword}")
//    public Result<List<SpuIndexVO>> selectSpuIndexVo(@PathVariable("keyword") String keyword) {
//        List<SpuIndexVO> spuIndexVOs = shoppingService.selectSpuIndexVoByKeyword(keyword);
//        return Result.success(spuIndexVOs);
//    }

    @GetMapping("keyword/{keyword}")
    public Result<List<SpuIndexVO>> selectSpuIndexVo(@PathVariable("keyword") String keyword) {
        List<SpuIndexVO> spuIndexVOS = inventoryService.selectSpuIndexVoByKeyword(keyword);
        return Result.success(spuIndexVOS);
    }

    @GetMapping("product/{productId}")
    public Result<Object> selectSpuDetail(@PathVariable("productId") Long productId)
            throws JsonProcessingException {
        Map<String, Object> map = inventoryService.selectSpuDetailById(productId);
        return Result.success(map);
    }

}
