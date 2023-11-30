package com.hf.ordersystem.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hf.amqp.service.RabbitMQService;
import com.hf.cache.service.RedisService;
import com.hf.core.exception.ParamException;
import com.hf.core.model.dto.OrderDTO;
import com.hf.core.model.dto.OrderDetailDTO;
import com.hf.core.model.dto.OrderPayInfo;
import com.hf.core.model.entity.order.Order;
import com.hf.core.model.entity.order.OrderDetail;
import com.hf.ordersystem.mapper.OrderDetailMapper;
import com.hf.ordersystem.mapper.OrderMapper;
import com.hf.ordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hf.cache.constants.RedisConstant.ORDER_PAY_INFO_KEY;
import static com.hf.cache.constants.RedisConstant.ORDER_PAY_INFO_TTL;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private RedisService redisService;

    //    @Override
//    public void submitOrder(OrderDTO orderdto) {
//        Order order = OrderDTO2Entity(orderdto);
//        int r1 = orderMapper.createOrder(order);
//        List<OrderDetailDTO> orderDetailDTOs = orderdto.getOrderDetailDTOs();
//        List<OrderDetail> orderDetails = new ArrayList<>();
//        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
//            OrderDetail orderDetail = OrderDetailDTO2Entity(orderDetailDTO);
//            orderDetail.setOrderNumber(order.getOrderNumber());
//            orderDetails.add(orderDetail);
//        }
//        int r2 = orderDetailMapper.createOrderDetails(orderDetails);
//        Map<String, Object> map = new HashMap<>();
//        map.put("order", order);
//        map.put("orderDetails", orderDetails);
//        rabbitMQService.convertAndSend(ORDER_INVENTORY_EXCHANGE, SUBMIT_ORDER, map);
//    }
    @Override
    public String submitOrder(OrderDTO orderdto) {
        Order order = OrderDTO2Entity(orderdto);
        int r1 = orderMapper.createOrder(order);
        List<OrderDetailDTO> orderDetailDTOs = orderdto.getOrderDetailDTOs();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
            OrderDetail orderDetail = OrderDetailDTO2Entity(orderDetailDTO);
            orderDetail.setOrderNumber(order.getOrderNumber());
            orderDetails.add(orderDetail);
        }
        int r2 = orderDetailMapper.createOrderDetails(orderDetails);
//    Map<String, Object> map = new HashMap<>();
//    map.put("order", order);
//    map.put("orderDetails", orderDetails);
//    rabbitMQService.convertAndSend(ORDER_INVENTORY_EXCHANGE, SUBMIT_ORDER, map);
        OrderPayInfo orderPayInfo = new OrderPayInfo();
        orderPayInfo.setOut_trade_no(order.getOrderNumber());
        orderPayInfo.setSubject("test");
        orderPayInfo.setTotal_amount(order.getOrderTotal().toString());
        orderPayInfo.setBody(JSONUtil.toJsonStr(orderDetailDTOs));
        String payInfoKey = ORDER_PAY_INFO_KEY + order.getOrderNumber();
        redisService.setCacheObject(payInfoKey, JSONUtil.toJsonStr(orderPayInfo), ORDER_PAY_INFO_TTL, TimeUnit.MINUTES);
        return order.getOrderNumber();
    }

    @Override
    public String updateStatus(String orderNumber) {
        if (StrUtil.isBlank(orderNumber)) {
            System.out.println("订单编号不可为空");
            throw new ParamException();
        }
        int result = orderMapper.updateOrderStatusByOrderNumber(orderNumber);
        return result > 0 ? "success" : "failure";
    }

    private Order OrderDTO2Entity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber("1");
        order.setOrderSource(orderDTO.getOrderSource());
        order.setOrderNote(orderDTO.getOrderNote());
        order.setOrderStatus(0);
        order.setOrderTotal(orderDTO.getProductTotal());
        order.setInvoiceTitle(orderDTO.getInvoiceTitle());
        order.setInvoiceContent(orderDTO.getInvoiceContent());
        order.setInvoiceType(orderDTO.getInvoiceType());
        order.setInvoiceReceiverPhone(orderDTO.getInvoiceReceiverPhone());
        order.setInvoiceReceiver(orderDTO.getInvoiceReceiver());
        order.setDeliveryMethod(orderDTO.getDeliveryMethod());
        order.setShippingFee(orderDTO.getShippingFee());
        order.setDeductionAmount(orderDTO.getDeductionAmount());
        order.setReceiverPhone(orderDTO.getReceiverPhone());
        order.setReceiverName(order.getReceiverName());
        order.setFirstLevelReceiverAddress(orderDTO.getFirstLevelReceiverAddress());
        order.setSecondLevelReceiverAddress(orderDTO.getSecondLevelReceiverAddress());
        order.setThirdLevelReceiverAddress(orderDTO.getThirdLevelReceiverAddress());
        order.setFourthLevelReceiverAddress(orderDTO.getFourthLevelReceiverAddress());
        order.setFifthLevelReceiverAddress(orderDTO.getFifthLevelReceiverAddress());
        return order;
    }

    private OrderDetail OrderDetailDTO2Entity(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPurchaseQuantity(orderDetailDTO.getPurchaseQuantity());
        orderDetail.setSkuImage(orderDetailDTO.getSkuImage());
        orderDetail.setSkuName(orderDetailDTO.getSkuName());
        orderDetail.setSkuPrice(orderDetailDTO.getSkuPrice());
        orderDetail.setSpuId(orderDetailDTO.getSpuId());
        orderDetail.setSkuId(orderDetailDTO.getSkuId());
        return orderDetail;
    }

}
