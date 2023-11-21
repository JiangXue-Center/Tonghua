package com.hf.ordersystem.mapper;

import com.hf.core.model.entity.order.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    public int createOrder(Order order);

}
