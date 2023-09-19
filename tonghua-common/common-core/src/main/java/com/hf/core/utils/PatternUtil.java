package com.hf.core.utils;

import cn.hutool.core.util.ReUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatternUtil {

    private static final Logger logger = LoggerFactory.getLogger(PatternUtil.class);


    public static Boolean isEmail(String email) {
        boolean isValidEmail = ReUtil.isMatch("^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", email);
        if (!isValidEmail) {
            logger.error("非正确邮箱格式");
            return false;
        }
        return true;
    }

    public static boolean isPhone(String phone) {
        boolean isValidPhoneNumber = ReUtil.isMatch("1[3456789]\\d{9}", phone);
        if (!isValidPhoneNumber) {
            logger.error("非正确手机号格式");
            return false;
        }
        return true;
    }

}
