package com.hf.userplatform.controller;


import com.hf.core.model.dto.RegisterDTO;
import com.hf.userplatform.model.entity.User;
import com.hf.userplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/email/{email}")
    public User getInfoByEmail(@PathVariable("email") String email) {
        User user = userService.selectUserByEmail(email);
        return user;
    }

    @GetMapping("/phone/{phone}")
    public User getInfoByPhone(@PathVariable("phone") String phone) {
        User user = userService.selectUserByPhone(phone);
        return user;
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody User user) {
        Boolean b = userService.register(user);
        return b;
    }



}
