package com.hf.core.enums;

public enum ExceptionEnums {

    ACCOUNT_PASSWORD_ERROR("账号或密码错误", 40001),
    CHECK_PASSWORD_ERROR("两次输入的密码不一致", 400002),
    TOKEN_GENARATE_ERROR("token生成失败", 40003),
    EMAIL_FORMAT_ERROR("邮箱格式错误", 40004),
    PHONE_FORMAT_ERROR("手机号格式错误", 40005),
    ACCOUNT_PASSWORD_NULL("账号或密码不可为空", 40006),
    LOGIN_MEHTOD_NULL("登录方式不可为空", 40007),
    LOGIN_METHOD_ERROR("登录方式错误", 40008),
    REGISTER_METHOD_ERROR("注册方式错误", 40009),
    REGISTER_TOKEN_ERROR("注册授权码不匹配", 40010);



    private String message;

    private Integer code;

    ExceptionEnums(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
