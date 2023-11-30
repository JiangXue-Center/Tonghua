package com.hf.ordersystem.controller;

import com.hf.core.model.Result;
import com.hf.core.model.dto.OrderDTO;
import com.hf.ordersystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String submitOrder(@RequestBody @Valid OrderDTO orderdto) {
        return orderService.submitOrder(orderdto);
    }

    @PutMapping
    public String updateOrderStatus(@RequestParam("orderNumber") String orderNumber) {
        return orderService.updateStatus(orderNumber);
    }

}
