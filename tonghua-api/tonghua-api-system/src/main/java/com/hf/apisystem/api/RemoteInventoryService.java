package com.hf.apisystem.api;

import com.hf.core.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteInventoryService", value = "module-inventory-system")
public interface RemoteInventoryService {

    @PutMapping("inventory/stock")
    public Result updateStock(@RequestParam("skuId") Long skuId,@RequestParam("num") Integer num);

}
