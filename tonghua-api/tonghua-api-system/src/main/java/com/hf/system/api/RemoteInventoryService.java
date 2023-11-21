package com.hf.system.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "remoteInventoryService", value = "module-inventory-system")
public interface RemoteInventoryService {



}
