package com.hf.shopping.controller;

import com.hf.core.model.Result;
import com.hf.shopping.model.vo.SpuIndexVO;
import com.hf.shopping.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result<Object> selectSpuDetail(@PathVariable("productId") Long productId) {
        inventoryService.selectSpuDetailById(productId);
        return null;
    }

}
