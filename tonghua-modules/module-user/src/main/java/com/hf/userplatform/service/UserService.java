package com.hf.userplatform.service;

import com.hf.core.model.entity.user.User;
import com.hf.core.model.vo.SimpleUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    User selectUserByEmail(String email);

    User selectUserByPhone(String phone);

    Boolean register(User user);

    Map<String, String> bindEmail(String email, String code);

    Map<String, String> bindPhone(String phone, String code);

    String followUser(String userId);

    List<SimpleUser> selectSimpleUsers(Set<String> userIds);

    List<SimpleUser> selectFriends(String path);

}
