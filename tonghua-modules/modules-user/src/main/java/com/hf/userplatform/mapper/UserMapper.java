package com.hf.userplatform.mapper;

import com.hf.core.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByEmailUser(String email);

    User selectByPhoneNumberUser(String phone);

    Integer register(User user);

}
