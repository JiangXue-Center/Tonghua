package com.hf.core.model;

import java.io.Serializable;

import static com.hf.core.constant.Constants.FAIL;
import static com.hf.core.constant.Constants.SUCCESS;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    private int code;

    private T data;

    public static <T> Result<T> success() {
        return rest(null, SUCCESS, null);
    }

    public static <T> Result<T> success(String message) {
        return rest(message, SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return rest(null, SUCCESS, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return rest(message, SUCCESS, data);
    }

    public static <T> Result<T> fail(String message, T data) {
        return rest(message, FAIL, data);
    }

    public static <T> Result<T> fail(String message) {
        return rest(message, FAIL, null);
    }

    public static <T> Result<T> fail(T data) {
        return rest(null, FAIL, data);
    }

    public static <T> Result<T> fail(int code) {
        return rest(null, code, null);
    }

    public static <T> Result<T> fail(String message, int code) {
        return rest(message, code, null);
    }

    public static <T> Result<T> fail(int code, T data) {
        return rest(null, code, data);
    }

    private static <T> Result<T> rest(String message, int code, T data) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        result.setData(data);
        return result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
