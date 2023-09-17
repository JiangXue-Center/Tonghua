package com.hf.auth.strategy.code;

import com.hf.auth.service.IMailService;
import com.hf.cache.service.RedisService;
import org.springframework.mail.javamail.JavaMailSender;

public interface SendCodeStrategy {

    void send(IMailService mailService, String to,String code, Integer method);

}
