package com.hf.auth.constants;

public interface Constant {

    public static final String SUBJECT = "同画";

    public static final String LOGIN_EMAIL_CONTENT = "您的验证码为：{0}, 该验证码5分钟内有效，请勿泄露于他人";

    public static final Integer METHOD_EMAIL_LOGIN = 1;

    public static final Integer METHOD_EMAIL_BIND = 2;

    public static final Integer METHOD_PHONE_LOGIN = 3;

    public static final Integer METHOD_PHONE_BIND = 4;

    public static final Integer METHOD_EMAIL_FIND_OUT = 5;

    public static final Integer METHOD_PHONE_FIND_OUT = 6;

    public final Integer[] METHOD_EMAIL = {METHOD_EMAIL_LOGIN, METHOD_EMAIL_BIND, METHOD_EMAIL_FIND_OUT};

    public final Integer[] METHOD_PHONE = {METHOD_PHONE_LOGIN, METHOD_PHONE_BIND, METHOD_PHONE_FIND_OUT};

    public static final Integer RANDOM_USER_NAME_LENGTH = 16;

    public static final String REGISTER_RESULT = "result";


}
