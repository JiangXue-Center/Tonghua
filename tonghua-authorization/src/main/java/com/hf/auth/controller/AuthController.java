package com.hf.auth.controller;

import com.hf.auth.service.LoginService;
import com.hf.core.model.Result;
import com.hf.core.model.dto.LoginDTO;
import com.hf.core.model.dto.RegisterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.hf.auth.constants.Constant.REGISTER_RESULT;
import static com.hf.core.constant.Constants.ACCESS_TOKEN;
import static com.hf.core.constant.Constants.REGISTER_TOKEN;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        logger.info("auth: 登录接口(login)请求体 {}", loginDTO);
        String token = loginService.login(loginDTO);

        Map<String, String> map = new HashMap<>();
        map.put(ACCESS_TOKEN, token);
        return Result.success(map);
    }

    @PostMapping("/register")
    public Result<Map<String, String>> register(@RequestBody RegisterDTO registerDTO) {
        logger.info("auth: 注册接口(register)请求体 {}", registerDTO);
        String str = loginService.register(registerDTO);
        Map<String, String> map = new HashMap<>();
        map.put(REGISTER_RESULT, str);
        return Result.success(map);
    }

    //1-登录注册邮箱验证码  2-绑定邮箱验证码  3-登录注册手机验证码
    //4-绑定手机号验证码 5-找回密码邮箱验证码 6-找回密码手机验证码
    @PostMapping("/code")
    public void code(@RequestParam String certificate,
                     @RequestParam Integer method) {
        loginService.sendCode(certificate, method);
    }

    @PostMapping("/check")
    public Result<Map<String, String>> check(@RequestParam String certificate,
                                             @RequestParam Integer method,
                                             @RequestParam String code) {
        String registerToken = loginService.checkCode(certificate, method, code);
        //todo 返回一个凭证 token
        Map<String, String> map = new HashMap<>();
        map.put(REGISTER_TOKEN, registerToken);
        return Result.success(map);
    }

}
