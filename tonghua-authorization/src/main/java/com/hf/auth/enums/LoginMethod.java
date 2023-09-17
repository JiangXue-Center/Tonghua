package com.hf.auth.enums;

public enum LoginMethod {

    EMAIL_CODE(1),
    EMAIL_PASSWORD(2),
    PHONE_CODE(3),
    PHONE_PASSWORD(4);

    private Integer method;

    LoginMethod(Integer method) {
        this.method = method;
    }

    public Integer getMethod() {
        return method;
    }

    public static LoginMethod fromMethod(int method) {
        for (LoginMethod loginMethod : LoginMethod.values()) {
            if (loginMethod.getMethod() == method) {
                return loginMethod;
            }
        }

        throw new RuntimeException("枚举转换异常");
    }


}
