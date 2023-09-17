package com.hf.auth.strategy.code.impl;

import com.hf.auth.service.IMailService;
import com.hf.auth.strategy.code.SendCodeStrategy;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static com.hf.auth.constants.EmailConstant.LOGIN_EMAIL_CONTENT;
import static com.hf.auth.constants.EmailConstant.SUBJECT;

@Component
public class PhoneSenderStrategyImpl implements SendCodeStrategy {

    @Override
    public void send(IMailService mailService, String to, String code, Integer method) {
    }

}
