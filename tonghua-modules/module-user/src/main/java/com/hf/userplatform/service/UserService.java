package com.hf.userplatform.service;

import com.hf.core.model.Result;
import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.model.entity.User;

import java.util.Map;

public interface UserService {
    User selectUserByEmail(String email);

    User selectUserByPhone(String phone);

    Boolean register(User user);

    Map<String, String> bindEmail(String email, String code);

    Map<String, String> bindPhone(String phone, String code);
}
