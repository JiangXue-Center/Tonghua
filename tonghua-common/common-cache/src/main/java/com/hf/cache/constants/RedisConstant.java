package com.hf.cache.constants;

public interface RedisConstant {

    public static final String LOGIN_TOKEN_KEY = "login:token:";

    public static final Long LOGIN_TOKEN_TTL = 1800L;

    public static final String LOGIN_EMAIL_CODE_KEY = "email:login:code:";

    public static final String BIND_EMAIL_CODE_KEY = "email:bind:code:";

    public static final String FIND_OUT_EMAIL_CODE_KEY = "email:findout:code:";

    public static final String LOGIN_PHONE_CODE_KEY = "phone:login:code";

    public static final String BIND_PHONE_CODE_KEY = "phone:bind:code";

    public static final String FIND_OUT_PHONE_CODE_KEY = "phone:findout:code";

    public static final Long LOGIN_CODE_TTL = 300L;

    public static final String REGISTER_EMAIL_TOKEN_KEY = "register:email:token:";

    public static final String REGISTER_PHONE_TOKEN_KEY = "register:phone:token:";



}
