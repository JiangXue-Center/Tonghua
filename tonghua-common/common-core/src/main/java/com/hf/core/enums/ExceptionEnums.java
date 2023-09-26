package com.hf.core.enums;

public enum ExceptionEnums {

    ACCOUNT_PASSWORD_ERROR("账号或密码错误", 40001),
    ACCOUNT_CODE_ERROR("账号或验证码错误", 40002),
    CHECK_PASSWORD_ERROR("两次输入的密码不一致", 40003),
    TOKEN_GENARATE_ERROR("token生成失败", 40004),
    EMAIL_FORMAT_ERROR("邮箱格式错误", 40005),
    PHONE_FORMAT_ERROR("手机号格式错误", 40006),
    ACCOUNT_PASSWORD_NULL("账号或密码不可为空", 40007),
    LOGIN_MEHTOD_NULL("登录方式不可为空", 40008),
    LOGIN_METHOD_ERROR("登录方式错误", 40009),
    REGISTER_METHOD_ERROR("注册方式错误", 400010),
    REGISTER_TOKEN_ERROR("注册授权码不匹配", 40011),
    BIND_EMAIL_ERROR("绑定邮箱失败", 40012),
    BIND_PHONE_ERROR("绑定手机号失败", 40013),
    USER_EXIST_ERROR("该用户已存在", 40014);




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
