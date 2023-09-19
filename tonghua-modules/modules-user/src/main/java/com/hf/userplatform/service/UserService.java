package com.hf.userplatform.service;

import com.hf.core.model.Result;
import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.model.entity.User;

public interface UserService {
    User selectUserByEmail(String email);

    User selectUserByPhone(String phone);

    Boolean register(User user);

    Boolean bindEmail(String email);
}
