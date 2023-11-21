package com.hf.ordersystem.mapper;

import com.hf.core.model.entity.order.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    public int createOrderDetails(@Param("list") List<OrderDetail> orderDetails);

}
