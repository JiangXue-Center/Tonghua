package com.hf.core.exception;

import com.hf.core.enums.ExceptionEnums;

public class AuthException extends RuntimeException{

    private final Integer code;

    public AuthException(ExceptionEnums enums) {
        super(enums.getMessage());
        this.code = enums.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
