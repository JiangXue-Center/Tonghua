package com.hf.userplatform.mapper;

import com.hf.core.model.entity.user.User;
import com.hf.core.model.vo.SimpleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserMapper {

    Map<String,String> selectUsernameAndAvatar(@Param("userId") String userId);

    User selectByEmailUser(String email);

    User selectByPhoneNumberUser(String phone);

    int register(User user);

    int bindEmail(String email, String id);

    int bindPhone(String phone, String id);

    String selectByPhoneOrEmail(String phone, String email);

    List<SimpleUser> selectSimpleListByIds(@Param("userIds") Set<String> userIds);



}
