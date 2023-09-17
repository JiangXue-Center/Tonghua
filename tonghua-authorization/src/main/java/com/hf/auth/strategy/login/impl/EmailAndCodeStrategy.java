package com.hf.auth.strategy.login.impl;

import com.hf.auth.strategy.login.AbLoginStrategy;
import com.hf.cache.service.RedisService;
import com.hf.core.model.entity.User;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.system.api.RemoteUserService;
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
        User user = remoteUserService.getUserInfoByEmail(email);
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN_EMAIL_CODE_KEY);
        builder.append(email);
//        String code = redisService.getCacheObject(builder.toString());
        Object o = redisService.getCacheObject(builder.toString());
        checkCertificateAndVerifyCodeIsEqual(verifyCode, o.toString());
        return user;
    }


}
