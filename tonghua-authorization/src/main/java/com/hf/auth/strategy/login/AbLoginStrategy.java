package com.hf.auth.strategy.login;

import cn.hutool.core.util.StrUtil;

public abstract class AbLoginStrategy {

    protected void checkCertificateAndVerifyCodeIsNull(String certificate, String verifyCode) {
        if (StrUtil.hasBlank(certificate, verifyCode)) {
            //账号或登录凭证不能为空
            throw new RuntimeException("账号或登录凭证不能为空");
        }
    }

    protected void checkCertificateAndVerifyCodeIsEqual(String verifyCode, String password) {
        if(!StrUtil.equals(verifyCode, password)) {
            throw new RuntimeException("账号或登录凭证错误");
        }
    }

}
