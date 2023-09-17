package com.hf.auth.enums;

public enum CodeSenderEnum {


    EMAIL_LOGIN(1),
    EMAIL_BIND(2),
    PHONE_LOGIN(3),
    PHONE_BIND(4),
    EMAIL_FIND_OUT(5),
    PHONE_FIND_OUT(6);


    private Integer method;

    public Integer getMethod() {
        return method;
    }

    CodeSenderEnum(Integer method) {
        this.method = method;
    }

    public static CodeSenderEnum fromMethod(Integer method) {
        for (CodeSenderEnum var : CodeSenderEnum.values()) {
            if (var.method == method) {
                return var;
            }
        }
        throw new RuntimeException("枚举转换异常");
    }
}
