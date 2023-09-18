package com.hf.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.auth.enums.CodeSenderEnum;
import com.hf.auth.enums.LoginMethod;
import com.hf.auth.factory.CodeSenderStrategyFactory;
import com.hf.auth.service.IMailService;
import com.hf.auth.strategy.code.SendCodeStrategy;
import com.hf.cache.service.RedisService;
import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.model.entity.User;
import com.hf.auth.factory.LoginStratetyFactory;
import com.hf.core.model.dto.LoginDTO;
import com.hf.auth.service.LoginService;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.core.utils.JwtUtil;
import com.hf.core.utils.SpringBeanContext;
import com.hf.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hf.auth.constants.EmailConstant.METHOD_EMAIL_LOGIN;
import static com.hf.auth.constants.EmailConstant.METHOD_PHONE_LOGIN;
import static com.hf.cache.constants.RedisConstant.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SpringBeanContext context;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisService redisService;

    private static final ExecutorService EMAIL_POOL_EXECUTOR = Executors.newSingleThreadExecutor();



    @Override
    public String login(LoginDTO loginDTO) {
        String certificate = loginDTO.getCertificate();
        String verifyCode = loginDTO.getVerifyCode();
        Integer method = loginDTO.getMethod();
        LoginStratetyFactory factory = context.getBean(LoginStratetyFactory.class);
        LoginStrategy strategy = factory.getLoginStrategy(LoginMethod.fromMethod(method));
        User user = strategy.loginStrategy(certificate, verifyCode);

        Integer id = user.getId();
        String str = String.valueOf(id);
        String token = JwtUtil.createJwtToken(str);
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN_TOKEN_KEY);
        builder.append(str);
        //构造一个tokenKey
        String tokenKey = builder.toString();
        //存入redis
        redisService.setCacheObject(tokenKey, token, LOGIN_TOKEN_TTL, TimeUnit.SECONDS);

        //todo Redis
        return token;
    }

    //phone/code/method

    @Override
    public Boolean register(RegisterDTO registerDTO) {
        String certificate = registerDTO.getCertificate();
        String verifyCode = registerDTO.getVerifyCode();
        String checkPassword = registerDTO.getCheckPassword();
        Integer method = registerDTO.getMethod();
        if (StrUtil.hasBlank(certificate, verifyCode, checkPassword)) {
            throw new RuntimeException();
        }
        if (!StrUtil.equals(verifyCode, checkPassword)) {
            throw new RuntimeException();
        }
        User user = new User();
        user.setPassword(verifyCode);
        user.setUsername("");
        if (method == 1) {
            user.setPhoneNumber(certificate);
            user.setEmail("");
        } else {
            user.setPhoneNumber("");
            user.setEmail(certificate);
        }
        Boolean b = remoteUserService.registerUser(user);
        return b;
    }

    @Override
    public void sendCode(String certificate, Integer method) {
        CodeSenderStrategyFactory factory = context.getBean(CodeSenderStrategyFactory.class);
        SendCodeStrategy strategy = factory.getSendCodeStrategy(CodeSenderEnum.fromMethod(method));
        EMAIL_POOL_EXECUTOR.submit(new HandleSendCode(certificate, strategy, method));
    }

    @Override
    public Boolean checkCode(String certificate, Integer method, String code) {
        if (StrUtil.hasBlank(code)) {
            throw new RuntimeException("验证码不可为空");
        }
        StringBuilder builder = new StringBuilder();
        if (method == METHOD_EMAIL_LOGIN) {
            builder.append(LOGIN_EMAIL_CODE_KEY);
        } else if(method == METHOD_PHONE_LOGIN) {
            builder.append(LOGIN_PHONE_CODE_KEY);
        } else {
            throw new RuntimeException("注册方式有误");
        }
        builder.append(certificate);
        String key = builder.toString();
        String redisCode = redisService.getCacheObject(key);
        if (!StrUtil.equals(code, redisCode)) {
            throw new RuntimeException("验证码错误");
        }
        return true;
    }


    private class HandleSendCode implements Runnable {

        private final String account;


        //1-登录注册邮箱验证码  2-绑定邮箱验证码  3-登录注册手机验证码  4-绑定手机号验证码 5-找回密码邮箱验证码 6-找回密码手机验证码
        private final SendCodeStrategy sender;

        private final Integer method;

        public HandleSendCode(String account, SendCodeStrategy sender, Integer method) {
            this.account = account;
            this.sender = sender;
            this.method = method;
        }

        @Override
        public void run() {
            System.out.println("打印线程池信息");
            String code = RandomUtil.randomNumbers(6);
            sender.send(account, code, method);
        }
    }
}
