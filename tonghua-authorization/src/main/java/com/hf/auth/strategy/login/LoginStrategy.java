package com.hf.auth.strategy.login;

import com.hf.core.model.entity.User;

public interface LoginStrategy {

    User loginStrategy(String certificate, String verifyCode);

}
