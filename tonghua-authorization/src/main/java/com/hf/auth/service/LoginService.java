package com.hf.auth.service;

import com.hf.core.model.dto.RegisterDTO;
import com.hf.core.model.entity.User;
import com.hf.core.model.dto.LoginDTO;

public interface LoginService {

    String login(LoginDTO loginDTO);

    Boolean register(RegisterDTO registerDTO);

    void sendCode(String certificate, Integer method);

    Boolean checkCode(String certificate, Integer method, String code);
}
