package com.hf.core.model.dto;

public class RegisterDTO extends LoginDTO{

    private String checkPassword;

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
