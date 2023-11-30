package com.hf.apisystem.api;

import com.hf.core.model.Result;
import com.hf.core.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteOrderService", value = "module-order-system")
public interface RemoteOrderService {

    @PostMapping("/order")
    public String submitOrder(@RequestBody OrderDTO orderdto);

    @PutMapping("/order")
    public String updateOrderStatus(@RequestParam("orderNumber") String orderNumber);
}
