package com.hf.auth.service;

public interface SmsService {

    void sendMsg(String phone, String code);
}
