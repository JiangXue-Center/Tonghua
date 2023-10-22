package com.hf.system.api;

import com.hf.core.model.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteUserService", value = "module-userplatform")
public interface RemoteUserService {

    @GetMapping("/user/phone/{phone}")
    public User getUserInfoByPhone(@PathVariable("phone") String phone);

    @GetMapping("/user/email/{email}")
    public User getUserInfoByEmail(@PathVariable("email") String email);

    @PostMapping("/user/register")
    public Boolean registerUser(@RequestBody User user);

}
