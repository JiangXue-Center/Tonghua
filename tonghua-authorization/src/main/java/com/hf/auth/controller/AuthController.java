package com.hf.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.model.dto.LoginDTO;
import com.hf.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@Validated @RequestBody LoginDTO loginDTO) {
        String token = loginService.login(loginDTO);
        if (StrUtil.hasBlank(token)) {
            return "发生未知错误";
        }
        return token;
    }

    @PostMapping("/register")
    public Boolean register(@Validated @RequestBody RegisterDTO registerDTO) {
        return loginService.register(registerDTO);
    }

    @PostMapping("/code")
    public void code(@RequestParam String certificate,
                     @RequestParam Integer method) {
        loginService.sendCode(certificate, method);
    }
}
