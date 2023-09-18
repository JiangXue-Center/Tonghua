package com.hf.auth.factory;

import cn.hutool.core.util.NumberUtil;
import com.hf.auth.enums.CodeSenderEnum;
import com.hf.auth.strategy.code.SendCodeStrategy;
import com.hf.auth.strategy.code.impl.EmailSenderStrategyImpl;
import com.hf.auth.strategy.code.impl.PhoneSenderStrategyImpl;
import com.hf.core.utils.SpringBeanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

import static com.hf.auth.constants.EmailConstant.*;

@Component
public class CodeSenderStrategyFactory {

    @Autowired
    private SpringBeanContext context;



    public SendCodeStrategy getSendCodeStrategy(CodeSenderEnum codeSenderEnum) {
        Integer method = codeSenderEnum.getMethod();
        if (compare(method, METHOD_EMAIL)) {
            return context.getBean(EmailSenderStrategyImpl.class);
        } else if (compare(method, METHOD_PHONE)) {
            return context.getBean(PhoneSenderStrategyImpl.class);
        }
        return null;
    }


    private Boolean compare(Integer method, Integer[] enums) {
        for (Integer i : enums) {
            if (method == i) {
                return true;
            }
        }
        return false;
    }

}
