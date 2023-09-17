package com.hf.userplatform.service;

import com.hf.core.model.dto.RegisterDTO;
import com.hf.userplatform.model.entity.User;

public interface UserService {
    User selectUserByEmail(String email);

    User selectUserByPhone(String phone);

    Boolean register(User user);
}
