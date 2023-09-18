package com.hf.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.hf.auth.exception.exception.TokenGenerateException;
import com.hf.auth.service.LoginService;
import com.hf.core.model.Result;
import com.hf.core.model.dto.LoginDTO;
import com.hf.core.model.dto.RegisterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        logger.info("auth: 登录接口(login)请求体 {}", loginDTO);
        String token = loginService.login(loginDTO);
        if (StrUtil.hasBlank(token)) {
            logger.error("生成token失败");
            throw new TokenGenerateException();
        }
        return Result.success(token);
    }

    @PostMapping("/register")
    public Result<Boolean> register(@Validated @RequestBody RegisterDTO registerDTO) {
        Boolean b = loginService.register(registerDTO);
        return Result.success(b);
    }

    //1-登录注册邮箱验证码  2-绑定邮箱验证码  3-登录注册手机验证码
    //4-绑定手机号验证码 5-找回密码邮箱验证码 6-找回密码手机验证码
    @PostMapping("/code")
    public void code(@RequestParam String certificate,
                     @RequestParam Integer method) {
        loginService.sendCode(certificate, method);
    }

    @GetMapping("/check")
    public Result<Boolean> check(@RequestParam String certificate,
                                 @RequestParam Integer method,
                                 @RequestParam String code) {
        Boolean b = loginService.checkCode(certificate, method, code);
        return Result.success(b);
    }
}
