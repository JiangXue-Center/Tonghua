package com.hf.ordersystem.service;

import com.hf.core.model.entity.order.Order;
import com.hf.ordersystem.model.dto.OrderDTO;

public interface OrderService {
    void submitOrder(OrderDTO orderdto);
}
