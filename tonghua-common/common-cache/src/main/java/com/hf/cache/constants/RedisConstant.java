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

    public static final Long LOGIN_CODE_TTL = 30L;

    public static final String REGISTER_EMAIL_TOKEN_KEY = "register:email:token:";

    public static final String REGISTER_PHONE_TOKEN_KEY = "register:phone:token:";

    public static final String ORDER_PAY_INFO_KEY = "pay:info:";

    public static final Long ORDER_PAY_INFO_TTL = 30L;

    public static final String ONLINE_USER_SET_KEY = "tonghua:online:set";

    public static final String ONLINE_USER_KEY = "tonghua:online:user:";

    public static final String USER_FOLLOW_KEY = "tonghua:user:follow:";

    public static final String USER_FANS_KEY = "tonghua:user:fans:";

    public static final String USER_MESSAGE_LIST_KEY = "tonghua:message:list:";

    public static final String USER_BASE_INFO_KEY = "tonghua:user:base:";

    public static final Long USER_BASE_INFO_TTL = 30L;

    public static final Long USER_MESSAGE_LIST_TTL = 10L;


}
