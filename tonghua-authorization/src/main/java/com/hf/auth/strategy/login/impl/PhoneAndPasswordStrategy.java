package com.hf.auth.strategy.login.impl;

import com.hf.core.exception.PhoneFormatException;
import com.hf.auth.strategy.login.AbLoginStrategy;
import com.hf.core.model.entity.User;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.core.utils.EncryptionUtil;
import com.hf.core.utils.PatternUtil;
import com.hf.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneAndPasswordStrategy extends AbLoginStrategy
        implements LoginStrategy {

    @Autowired
    private RemoteUserService remoteUserService;


    @Override
    public User loginStrategy(String phone, String password) {
        checkCertificateAndVerifyCodeIsNull(phone, password);
        if (!PatternUtil.isPhone(phone)) {
            throw new PhoneFormatException();
        }
        User user = remoteUserService.getUserInfoByPhone(phone);
        String userPassword = user.getPassword();
        String salt = user.getSalt();
        String hashedPassword = EncryptionUtil.encryptionPassword(password, salt);
        checkVerifyCodeAndPasswordIsEqual(hashedPassword, userPassword);
        return user;
    }
}
