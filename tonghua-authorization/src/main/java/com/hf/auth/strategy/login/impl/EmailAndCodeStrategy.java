package com.hf.auth.strategy.login.impl;

import com.hf.core.exception.EmailFormatException;
import com.hf.auth.strategy.login.AbLoginStrategy;
import com.hf.cache.service.RedisService;
import com.hf.core.model.entity.user.User;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.core.utils.PatternUtil;
import com.hf.apisystem.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.hf.cache.constants.RedisConstant.*;

@Component
public class EmailAndCodeStrategy extends AbLoginStrategy
        implements LoginStrategy {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisService redisService;

    @Override
    public User loginStrategy(String email, String verifyCode) {
        checkCertificateAndVerifyCodeIsNull(email, verifyCode);
        if (!PatternUtil.isEmail(email)) {
            throw new EmailFormatException();
        }
        User user = remoteUserService.getUserInfoByEmail(email);
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN_EMAIL_CODE_KEY);
        builder.append(email);
        String code = redisService.getCacheObject(builder.toString());
        checkVerifyCodeAndCodeIsEqual(verifyCode, code);
        return user;
    }


}
