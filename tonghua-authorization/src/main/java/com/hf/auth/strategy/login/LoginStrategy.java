package com.hf.auth.strategy.login;

import com.hf.core.model.entity.user.User;

public interface LoginStrategy {

    User loginStrategy(String certificate, String verifyCode);

}
