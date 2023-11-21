package com.hf.ordersystem.controller;

import com.hf.core.model.Result;
import com.hf.core.model.entity.order.Order;
import com.hf.ordersystem.model.dto.OrderDTO;
import com.hf.ordersystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result createOrder(@RequestBody @Valid OrderDTO orderdto) {
        orderService.submitOrder(orderdto);
        return Result.success();
    }

}
