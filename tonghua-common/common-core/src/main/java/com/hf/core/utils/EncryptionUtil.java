package com.hf.core.utils;

import cn.hutool.crypto.digest.BCrypt;

public class EncryptionUtil {


//
//    public static Boolean checkEncryptionPassword(String password) {
//         String hashedPassword = encryptionPassword(password);
//        // 验证密码是否匹配
//        return BCrypt.checkpw(password, hashedPassword);
//    }

    public static String encryptionPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static String getSalt() {
        return BCrypt.gensalt(16);
    }
}
