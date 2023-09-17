package com.hf.auth.strategy.login.impl;

import com.hf.auth.strategy.login.AbLoginStrategy;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.core.model.entity.User;
import com.hf.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAndPasswordStrategy extends AbLoginStrategy
        implements LoginStrategy {

    @Autowired
    private RemoteUserService remoteUserService;


    @Override
    public User loginStrategy(String email, String password) {
        //检查账号密码是否为空
        checkCertificateAndVerifyCodeIsNull(email, password);
        User user = remoteUserService.getUserInfoByEmail(email);
        //检查密码或验证码是否正确
        checkCertificateAndVerifyCodeIsEqual(password, user.getPassword());

        return user;
    }


}
