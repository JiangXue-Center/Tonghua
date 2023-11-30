package com.hf.ordersystem.service;

import com.hf.core.model.dto.OrderDTO;

public interface OrderService {
    String submitOrder(OrderDTO orderdto);

    String updateStatus(String orderNumber);
}
