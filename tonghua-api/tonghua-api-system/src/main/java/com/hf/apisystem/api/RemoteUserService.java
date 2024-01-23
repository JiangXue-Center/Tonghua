package com.hf.apisystem.api;

import com.hf.core.model.entity.user.User;
import com.hf.core.model.vo.SimpleUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(contextId = "remoteUserService", value = "module-userplatform")
public interface RemoteUserService {

    @GetMapping("/user/phone/{phone}")
    public User getUserInfoByPhone(@PathVariable("phone") String phone);

    @GetMapping("/user/email/{email}")
    public User getUserInfoByEmail(@PathVariable("email") String email);

    @PostMapping("/user/register")
    public Boolean registerUser(@RequestBody User user);

    @PostMapping("/user/simpleList")
    public List<SimpleUser> selectSimpleUsers(@RequestBody Set<String> userIds);

}
