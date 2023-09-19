package com.hf.auth.strategy.login.impl;

import com.hf.core.exception.PhoneFormatException;
import com.hf.auth.strategy.login.AbLoginStrategy;
import com.hf.cache.service.RedisService;
import com.hf.core.model.entity.User;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.core.utils.PatternUtil;
import com.hf.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.hf.cache.constants.RedisConstant.LOGIN_PHONE_CODE_KEY;

@Component
public class PhoneAndCodeStrategy extends AbLoginStrategy
        implements LoginStrategy {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisService redisService;

    @Override
    public User loginStrategy(String phone, String verifyCode) {
        checkCertificateAndVerifyCodeIsNull(phone, verifyCode);
        if (!PatternUtil.isPhone(phone)) {
            throw new PhoneFormatException();
        }
        User user = remoteUserService.getUserInfoByPhone(phone);
        String str = String.valueOf(user.getId());
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN_PHONE_CODE_KEY);
        builder.append(str);
        String code = redisService.getCacheObject(builder.toString());
        checkCertificateAndVerifyCodeIsEqual(verifyCode, code);
        return user;
    }

}
