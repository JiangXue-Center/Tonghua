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

import static com.hf.auth.enums.LoginMethod.EMAIL_CODE;

@Component
public class LoginStratetyFactory {

    @Autowired
    private SpringBeanContext context;

    public LoginStrategy getLoginStrategy(LoginMethod method) {
        switch (method) {
            case EMAIL_CODE:
                return context.getBean(EmailAndCodeStrategy.class);
            case EMAIL_PASSWORD:
                return context.getBean(EmailAndPasswordStrategy.class);
            case PHONE_CODE:
                return context.getBean(PhoneAndCodeStrategy.class);
            case PHONE_PASSWORD:
                return context.getBean(PhoneAndPasswordStrategy.class);
        }
        return null;
    }


}

