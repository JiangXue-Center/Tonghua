package com.hf.userplatform.mapper;

import com.hf.core.model.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByEmailUser(String email);

    User selectByPhoneNumberUser(String phone);

    int register(User user);

    int bindEmail(String email, String id);

    int bindPhone(String phone, String id);

    String selectByPhoneOrEmail(String phone, String email);



}
