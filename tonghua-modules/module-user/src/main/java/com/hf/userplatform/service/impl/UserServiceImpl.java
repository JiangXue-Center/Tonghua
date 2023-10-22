package com.hf.userplatform.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hf.cache.service.RedisService;
import com.hf.core.exception.AuthException;
import com.hf.core.exception.BindException;
import com.hf.core.exception.EmailFormatException;
import com.hf.core.exception.PhoneFormatException;
import com.hf.core.model.entity.user.User;
import com.hf.core.utils.PatternUtil;
import com.hf.userplatform.mapper.UserMapper;
import com.hf.userplatform.service.UserService;
import com.hf.userplatform.utils.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.hf.cache.constants.RedisConstant.BIND_EMAIL_CODE_KEY;
import static com.hf.cache.constants.RedisConstant.BIND_PHONE_CODE_KEY;
import static com.hf.core.enums.ExceptionEnums.USER_EXIST_ERROR;
import static com.hf.userplatform.constants.Constant.BIND_SUCCESS;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectByEmailUser(email);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userMapper.selectByPhoneNumberUser(phone);
    }

    @Override
    public Boolean register(User user) {
        String phone = user.getPhoneNumber();
        String email = user.getEmail();
        String id = userMapper.selectByPhoneOrEmail(phone, email);
        if (!StrUtil.hasBlank(id)) {
            throw new AuthException(USER_EXIST_ERROR);
        }
        int b = userMapper.register(user);
        return b > 0;
    }

    @Override
    public Map<String, String> bindEmail(String email, String code) {
        if (StrUtil.hasBlank(email)) {
            throw new EmailFormatException();
        }
        if (!PatternUtil.isEmail(email)) {
            throw new EmailFormatException();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(BIND_EMAIL_CODE_KEY);
        builder.append(email);
        String key = builder.toString();
        String redisCode = redisService.getCacheObject(key);
        if (!StrUtil.equals(code, redisCode)) {
            throw new RuntimeException();
        }
        String id = TokenHolder.get();
        int result = userMapper.bindEmail(email, id);
        if (result == 0) {
            throw new BindException();
        }
        Map<String, String> map = new HashMap<>();
        map.put("result", BIND_SUCCESS);

        return map;
    }

    @Override
    public Map<String, String> bindPhone(String phone, String code) {
        if (StrUtil.hasBlank(phone, code)) {
            throw new PhoneFormatException();
        }
        if (!PatternUtil.isPhone(phone)) {
            throw new EmailFormatException();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(BIND_PHONE_CODE_KEY);
        builder.append(phone);
        String key = builder.toString();
        String redisCode = redisService.getCacheObject(key);
        if (!StrUtil.equals(code, redisCode)) {
            throw new RuntimeException();
        }
        String id = TokenHolder.get();
        int result = userMapper.bindPhone(phone, id);
        if (result == 0) {
            throw new BindException();
        }
        Map<String, String> map = new HashMap<>();
        map.put("result", BIND_SUCCESS);
        return map;
    }
}
