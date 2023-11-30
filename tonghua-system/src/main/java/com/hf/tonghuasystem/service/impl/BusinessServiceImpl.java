package com.hf.tonghuasystem.service.impl;

import com.hf.amqp.service.RabbitMQService;
import com.hf.core.model.dto.OrderDTO;
import com.hf.core.model.dto.OrderDetailDTO;
import com.hf.apisystem.api.RemoteInventoryService;
import com.hf.apisystem.api.RemoteOrderService;
import com.hf.tonghuasystem.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hf.amqp.constants.MQConstants.ORDER_PAY_STATUS_EXCHANGE;
import static com.hf.amqp.constants.MQConstants.PAY_STATUS;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private RemoteOrderService remoteOrderService;

    @Autowired
    private RemoteInventoryService remoteInventoryService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    @GlobalTransactional
    public String submitOrder(OrderDTO orderDTO) {
        String orderNumber = remoteOrderService.submitOrder(orderDTO);
        List<OrderDetailDTO> orderDetailDTOs = orderDTO.getOrderDetailDTOs();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
            Long skuId = orderDetailDTO.getSkuId();
            Integer num = orderDetailDTO.getPurchaseQuantity();
            System.out.println("skuId" + skuId);
            System.out.println("purchase" + num);
            remoteInventoryService.updateStock(skuId, num);
        }
        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", orderNumber);
        rabbitMQService.convertAndSend(ORDER_PAY_STATUS_EXCHANGE, PAY_STATUS, map);
        return orderNumber;
    }
}
