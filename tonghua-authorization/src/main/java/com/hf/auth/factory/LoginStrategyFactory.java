package com.hf.auth.factory;

import com.hf.auth.enums.LoginMethod;
import com.hf.auth.strategy.login.LoginStrategy;
import com.hf.auth.strategy.login.impl.EmailAndCodeStrategy;
import com.hf.auth.strategy.login.impl.EmailAndPasswordStrategy;
import com.hf.auth.strategy.login.impl.PhoneAndCodeStrategy;
import com.hf.auth.strategy.login.impl.PhoneAndPasswordStrategy;
import com.hf.core.utils.SpringBeanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginStrategyFactory {

    @Autowired
    private SpringBeanContext context;

    public LoginStrategy getLoginStrategy(LoginMethod method) {
        return switch (method) {
            case EMAIL_CODE -> context.getBean(EmailAndCodeStrategy.class);
            case EMAIL_PASSWORD -> context.getBean(EmailAndPasswordStrategy.class);
            case PHONE_CODE -> context.getBean(PhoneAndCodeStrategy.class);
            case PHONE_PASSWORD -> context.getBean(PhoneAndPasswordStrategy.class);
        };
    }


}

