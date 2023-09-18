package com.hf.core.enums;

public enum ExceptionEnums {

    EMAIL_PASSWORD_ERROR("邮箱或密码错误", 40001),
    TOKEN_GENARATE_ERROR("token生成失败", 40002);

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
