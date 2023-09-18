package com.hf.auth.strategy.code.impl;

import cn.hutool.json.JSONUtil;
import com.hf.auth.service.SmsService;
import com.hf.auth.strategy.code.SendCodeStrategy;
import com.hf.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.hf.auth.constants.EmailConstant.*;
import static com.hf.cache.constants.RedisConstant.*;

@Component
public class PhoneSenderStrategyImpl implements SendCodeStrategy {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsService smsService;

    @Override
    public void send(String to, String code, Integer method) {
        smsService.sendMsg(to, code);
        StringBuilder builder = new StringBuilder();
        builder.append(getKey(method));
        builder.append(to);
        String key = builder.toString();
        String jsonStr = JSONUtil.toJsonStr(code);
        redisService.setCacheObject(key, jsonStr, LOGIN_CODE_TTL, TimeUnit.SECONDS);
        System.out.println("成功发送短信");
    }

    private String getKey(Integer method) {
        String prefix = new String();
        if (method == METHOD_PHONE_LOGIN) {
            prefix = LOGIN_PHONE_CODE_KEY;
        }
        if (method == METHOD_PHONE_BIND) {
            prefix = BIND_PHONE_CODE_KEY;
        }
        if (method == METHOD_PHONE_FIND_OUT) {
            prefix = FIND_OUT_PHONE_CODE_KEY;
        }
        return prefix;

    }
}
