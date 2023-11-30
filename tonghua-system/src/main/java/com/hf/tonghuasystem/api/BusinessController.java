package com.hf.tonghuasystem.api;

import com.hf.core.model.Result;
import com.hf.core.model.dto.OrderDTO;
import com.hf.tonghuasystem.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biz")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("order")
    public Result<String> createOrder(@RequestBody OrderDTO orderdto) {
        String orderNumber = businessService.submitOrder(orderdto);
        return Result.success(orderNumber);
    }

}
