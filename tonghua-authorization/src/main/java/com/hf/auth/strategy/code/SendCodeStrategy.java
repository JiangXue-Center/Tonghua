package com.hf.auth.strategy.code;

public interface SendCodeStrategy {

    void send(String to, String code, Integer method);

}
