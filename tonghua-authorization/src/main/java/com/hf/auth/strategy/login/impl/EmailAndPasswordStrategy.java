package com.hf.auth.strategy.login.impl;

import cn.hutool.core.util.ObjectUtil;
import com.hf.core.enums.ExceptionEnums;
import com.hf.core.exception.AuthException;
import com.hf.core.exception.EmailFormatException;
import com.hf.auth.strategy.login.AbLoginStrategy;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.core.model.entity.User;
import com.hf.core.utils.EncryptionUtil;
import com.hf.core.utils.PatternUtil;
import com.hf.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.hf.core.enums.ExceptionEnums.ACCOUNT_PASSWORD_ERROR;

@Component
public class EmailAndPasswordStrategy extends AbLoginStrategy
        implements LoginStrategy {

    @Autowired
    private RemoteUserService remoteUserService;


    @Override
    public User loginStrategy(String email, String password) {
        //检查账号密码是否为空
        checkCertificateAndVerifyCodeIsNull(email, password);
        if (!PatternUtil.isEmail(email)) {
            throw new EmailFormatException();
        }
        User user = remoteUserService.getUserInfoByEmail(email);
        if (ObjectUtil.isNull(user)) {
            throw new AuthException(ACCOUNT_PASSWORD_ERROR);
        }
        //检查密码是否正确
        String userPassword = user.getPassword();
        String salt = user.getSalt();
        String hashedPassword = EncryptionUtil.encryptionPassword(password, salt);
        checkVerifyCodeAndPasswordIsEqual(hashedPassword, userPassword);
        return user;
    }


}
