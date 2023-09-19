package com.hf.userplatform.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.hf.core.exception.EmailFormatException;
import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.utils.JwtUtil;
import com.hf.core.utils.PatternUtil;
import com.hf.userplatform.mapper.UserMapper;
import com.hf.core.model.entity.User;
import com.hf.userplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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


        Integer b = userMapper.register(user);
        return b > 0;
    }

    @Override
    public Boolean bindEmail(String email) {
        if (StrUtil.hasBlank(email)) {
            throw new EmailFormatException();
        }
        if (!PatternUtil.isEmail(email)) {
            throw new EmailFormatException();
        }

        return null;
    }
}
