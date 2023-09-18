package com.hf.auth.strategy.code.impl;

import cn.hutool.json.JSONUtil;
import com.hf.auth.service.IMailService;
import com.hf.auth.strategy.code.SendCodeStrategy;
import com.hf.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static com.hf.auth.constants.EmailConstant.*;
import static com.hf.cache.constants.RedisConstant.*;

@Component
public class EmailSenderStrategyImpl implements SendCodeStrategy {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IMailService mailService;

    @Override
    public void send(String to, String code, Integer method) {
        System.out.println("打印邮件信息");
        String content = MessageFormat.format(LOGIN_EMAIL_CONTENT, code);
        mailService.sendSimpleMail(to, SUBJECT, content);
        StringBuilder builder = new StringBuilder();
        builder.append(getKey(method));
        builder.append(to);
        String key = builder.toString();
        String jsonStr = JSONUtil.toJsonStr(code);
        redisService.setCacheObject(key, jsonStr, LOGIN_CODE_TTL, TimeUnit.SECONDS);
        System.out.println("完成发送邮件");
    }


    private String getKey(Integer method) {
        String prefix = new String();
        if (method == METHOD_EMAIL_LOGIN) {
            prefix = LOGIN_EMAIL_CODE_KEY;
        }
        if (method == METHOD_EMAIL_BIND) {
            prefix = BIND_EMAIL_CODE_KEY;
        }
        if (method == METHOD_EMAIL_FIND_OUT) {
            prefix = FIND_OUT_EMAIL_CODE_KEY;
        }
        return prefix;

    }

}
