package com.hf.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.auth.enums.CodeSenderEnum;
import com.hf.auth.enums.LoginMethod;
import com.hf.auth.factory.CodeSenderStrategyFactory;
import com.hf.auth.factory.LoginStrategyFactory;
import com.hf.auth.service.LoginService;
import com.hf.auth.strategy.code.SendCodeStrategy;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.cache.service.RedisService;
import com.hf.core.exception.AuthException;
import com.hf.core.model.dto.LoginDTO;
import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.model.entity.user.User;
import com.hf.core.utils.EncryptionUtil;
import com.hf.core.utils.JwtUtil;
import com.hf.core.utils.SpringBeanContext;
import com.hf.apisystem.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hf.auth.constants.Constant.*;
import static com.hf.cache.constants.RedisConstant.*;
import static com.hf.core.constant.Constants.LOGIN_FAIL;
import static com.hf.core.constant.Constants.REGISTER;
import static com.hf.core.enums.ExceptionEnums.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SpringBeanContext context;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisService redisService;

    private static final ExecutorService EMAIL_POOL_EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();


    @Override
    public String login(LoginDTO loginDTO) {
        String certificate = loginDTO.getCertificate();
        String verifyCode = loginDTO.getVerifyCode();
        Integer method = loginDTO.getMethod();
        //检查账号和密码是否为空
        if (StrUtil.hasBlank(certificate, verifyCode)) {
            throw new AuthException(ACCOUNT_PASSWORD_NULL);
        }
        //检查登录方法是否为空
        if (ObjectUtil.isNull(method)) {
            throw new AuthException(LOGIN_METHOD_ERROR);
        }
        //检查登录方法是否正确
        checkLoginMethod(method);
        //获取策略模式工厂类并构建登录策略，获取用户信息
        LoginStrategyFactory factory = context.getBean(LoginStrategyFactory.class);
        //用工厂新建一个类
        LoginStrategy strategy = factory.getLoginStrategy(LoginMethod.fromMethod(method));
        User user = strategy.loginStrategy(certificate, verifyCode);

        //将数据存入redis
        String id = user.getId();
        String token = JwtUtil.createToken(id);
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN_TOKEN_KEY);
        builder.append(id);
        //构造一个tokenKey
        String tokenKey = builder.toString();
        //存入redis
        redisService.setCacheObject(tokenKey, token, LOGIN_TOKEN_TTL, TimeUnit.HOURS);

        return token;
    }

    //phone/code/method

    @Override
    public String register(RegisterDTO registerDTO) {
        String certificate = registerDTO.getCertificate();
        String verifyCode = registerDTO.getVerifyCode();
        String checkPassword = registerDTO.getCheckPassword();
        Integer method = registerDTO.getMethod();
        String registerToken = registerDTO.getRegisterToken();
        if (StrUtil.hasBlank(certificate, verifyCode, checkPassword)) {
            throw new AuthException(ACCOUNT_PASSWORD_NULL);
        }
        if (!StrUtil.equals(verifyCode, checkPassword)) {
            throw new AuthException(ACCOUNT_PASSWORD_ERROR);
        }
        if (ObjectUtil.isNull(method)) {
            throw new AuthException(LOGIN_MEHTOD_NULL);
        }
        if (method != METHOD_EMAIL_LOGIN && method != METHOD_PHONE_LOGIN) {
            throw new AuthException(REGISTER_METHOD_ERROR);
        }
        StringBuilder builder = new StringBuilder();
        User user = new User();

        user.setUsername(RandomUtil.randomString(RANDOM_USER_NAME_LENGTH));
        if (method == METHOD_PHONE_LOGIN) {
            builder.append(REGISTER_PHONE_TOKEN_KEY);
            user.setPhoneNumber(certificate);
            user.setEmail("");
        } else {
            builder.append(REGISTER_EMAIL_TOKEN_KEY);
            user.setPhoneNumber("");
            user.setEmail(certificate);
        }
        builder.append(certificate);
        //构建注册授权吗register_token的key
        String registerTokenKey = builder.toString();
        String redisToken = redisService.getCacheObject(registerTokenKey);
        if (!StrUtil.equals(registerToken, redisToken)) {
            throw new AuthException(REGISTER_TOKEN_ERROR);
        }

        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        String salt = EncryptionUtil.getSalt();
        String encryptionPassword = EncryptionUtil.encryptionPassword(verifyCode, salt);
        user.setSalt(salt);
        user.setPassword(encryptionPassword);

        Boolean b = remoteUserService.registerUser(user);
        return b ? REGISTER:LOGIN_FAIL;
    }

    @Override
    public void sendCode(String certificate, Integer method) {
        CodeSenderStrategyFactory factory = context.getBean(CodeSenderStrategyFactory.class);
        SendCodeStrategy strategy = factory.getSendCodeStrategy(CodeSenderEnum.fromMethod(method));
        EMAIL_POOL_EXECUTOR.submit(new HandleSendCode(certificate, strategy, method));
    }

    @Override
    public String checkCode(String certificate, Integer method, String code) {
        if (StrUtil.hasBlank(code)) {
            throw new RuntimeException("验证码不可为空");
        }
        //构建验证码以及验证token的key
        StringBuilder builder = new StringBuilder();
        StringBuilder tokenBuilder = new StringBuilder();
        if (method == METHOD_EMAIL_LOGIN) {
            builder.append(LOGIN_EMAIL_CODE_KEY);
            tokenBuilder.append(REGISTER_EMAIL_TOKEN_KEY);
        } else if (method == METHOD_PHONE_LOGIN) {
            builder.append(LOGIN_PHONE_CODE_KEY);
            tokenBuilder.append(REGISTER_PHONE_TOKEN_KEY);
        } else {
            throw new RuntimeException("注册方式有误");
        }
        builder.append(certificate);
        tokenBuilder.append(certificate);
        String codeKey = builder.toString();
        //获取验证码以及验证token
        String redisCode = redisService.getCacheObject(codeKey);
        if (!StrUtil.equals(code, redisCode)) {
            throw new RuntimeException("验证码错误");
        }
        String registerToken = UUID.randomUUID().toString();
        String tokenKey = tokenBuilder.toString();
        redisService.setCacheObject(tokenKey, registerToken, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        return registerToken;
    }

    private void checkLoginMethod(Integer method) {
        LoginMethod[] values = LoginMethod.values();
        int sum = 0;
        for (LoginMethod value : values) {
            if (value.getMethod() != method) {
                sum++;
            }
        }
        if (sum == values.length) {
            throw new AuthException(LOGIN_METHOD_ERROR);
        }
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
