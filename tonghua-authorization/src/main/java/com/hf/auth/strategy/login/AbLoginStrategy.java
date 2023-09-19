package com.hf.auth.strategy.login;

import cn.hutool.core.util.StrUtil;
import com.hf.core.exception.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.hf.core.enums.ExceptionEnums.ACCOUNT_PASSWORD_ERROR;

public abstract class AbLoginStrategy {

    private static final Logger logger = LoggerFactory.getLogger(AbLoginStrategy.class);

    protected void checkCertificateAndVerifyCodeIsNull(String certificate, String verifyCode) {
        if (StrUtil.hasBlank(certificate, verifyCode)) {
            //账号或登录凭证不能为空
            throw new RuntimeException("账号或登录凭证不能为空");
        }
    }

    protected void checkCertificateAndVerifyCodeIsEqual(String verifyCode, String password) {
        if(!StrUtil.equals(verifyCode, password)) {
            throw new AuthException(ACCOUNT_PASSWORD_ERROR);
        }
    }


}
