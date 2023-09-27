package com.hf.userplatform.utils;

public class TokenHolder {

    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void save(String token) {
        tl.set(token);
    }

    public static String get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }



}
