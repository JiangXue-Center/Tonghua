package com.hf.auth.strategy.login;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbLoginStrategy {

    private static final Logger logger = LoggerFactory.getLogger(AbLoginStrategy.class);

    protected void checkCertificateAndVerifyCodeIsNull(String certificate, String verifyCode) {
        if (StrUtil.hasBlank(certificate, verifyCode)) {
            //账号或登录凭证不能为空
            throw new RuntimeException("账号或登录凭证不能为空");
        }
    }

    protected void checkCertificateAndVerifyCodeIsEqual(String verifyCode, String password) {
        if (!StrUtil.equals(verifyCode, password)) {
            throw new RuntimeException("账号或登录凭证错误");
        }
    }

    protected void isEmail(String email) {
        boolean isValidEmail = ReUtil.isMatch("^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", email);
        if (!isValidEmail) {
            logger.error("非正确邮箱格式");
        }
    }

    protected void isPhone(String phone) {
        boolean isValidPhoneNumber = ReUtil.isMatch("1[3456789]\\d{9}", phone);
        if (!isValidPhoneNumber) {
            logger.error("非正确手机号格式");
        }
    }

}
