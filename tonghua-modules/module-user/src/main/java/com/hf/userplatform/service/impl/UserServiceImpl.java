package com.hf.userplatform.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hf.cache.service.RedisService;
import com.hf.core.exception.*;
import com.hf.core.model.entity.user.User;
import com.hf.core.model.vo.SimpleUser;
import com.hf.core.utils.PatternUtil;
import com.hf.minio.service.MinIOService;
import com.hf.userplatform.mapper.UserMapper;
import com.hf.userplatform.service.UserService;
import com.hf.core.utils.TokenHolder;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hf.cache.constants.RedisConstant.*;
import static com.hf.core.enums.ExceptionEnums.USER_EXIST_ERROR;
import static com.hf.userplatform.constants.Constant.BIND_SUCCESS;


@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MinIOService minIOService;


    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectByEmailUser(email);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userMapper.selectByPhoneNumberUser(phone);
    }

    @Override
    public Boolean register(User user) {
        String phone = user.getPhoneNumber();
        String email = user.getEmail();
        String id = userMapper.selectByPhoneOrEmail(phone, email);
        if (!StrUtil.hasBlank(id)) {
            throw new AuthException(USER_EXIST_ERROR);
        }
        int b = userMapper.register(user);
        return b > 0;
    }

    @Override
    public Map<String, String> bindEmail(String email, String code) {
        if (StrUtil.hasBlank(email)) {
            throw new EmailFormatException();
        }
        if (!PatternUtil.isEmail(email)) {
            throw new EmailFormatException();
        }
        String key = BIND_EMAIL_CODE_KEY + email;
        String redisCode = redisService.getCacheObject(key);
        if (!StrUtil.equals(code, redisCode)) {
            throw new RuntimeException();
        }
        String id = TokenHolder.get();
        int result = userMapper.bindEmail(email, id);
        if (result == 0) {
            throw new BindException();
        }
        Map<String, String> map = new HashMap<>();
        map.put("result", BIND_SUCCESS);

        return map;
    }

    @Override
    public Map<String, String> bindPhone(String phone, String code) {
        if (StrUtil.hasBlank(phone, code)) {
            throw new PhoneFormatException();
        }
        if (!PatternUtil.isPhone(phone)) {
            throw new EmailFormatException();
        }
        String key = BIND_PHONE_CODE_KEY + phone;
        String redisCode = redisService.getCacheObject(key);
        if (!StrUtil.equals(code, redisCode)) {
            throw new RuntimeException();
        }
        String id = TokenHolder.get();
        int result = userMapper.bindPhone(phone, id);
        if (result == 0) {
            throw new BindException();
        }
        Map<String, String> map = new HashMap<>();
        map.put("result", BIND_SUCCESS);
        return map;
    }

    @Override
    public String followUser(String followId) {
        logger.info("关注用户的id {}", followId);
        if (StrUtil.hasBlank(followId)) {
            throw new ParamException("要关注的用户id为空");
        }
        String id = TokenHolder.get();
        String followKey = USER_FOLLOW_KEY + id;
        String fansKey = USER_FANS_KEY + followId;
        //把对方加入自己的关注列表
        redisService.add2Set(followKey, followId);
        //把自己加入对方的粉丝列表
        redisService.add2Set(fansKey, id);
        return "关注成功";
    }

    @Override
    public List<SimpleUser> selectSimpleUsers(Set<String> userIds) {
        if (userIds.isEmpty()) {
//            throw new ParamException("用户Id集合为空 ");
            return Collections.emptyList();
        }
        List<SimpleUser> simpleUsers = userMapper.selectSimpleListByIds(userIds);
        logger.info("selectSimpleUser查询结果为 {}", simpleUsers);
        return simpleUsers;
    }

    @Override
    public List<SimpleUser> selectFriends(String path) {
        logger.info("请求路径为 {}", path);
        String userId = TokenHolder.get();
        String key = "";
        if (path.endsWith("follows")) {
            key = USER_FOLLOW_KEY + userId;
        } else {
            key = USER_FANS_KEY + userId;
        }
        Set<String> followIds = redisService.getCacheSet(key);
        if (followIds.isEmpty()) {
            logger.info("用户" + userId + "的关注或粉丝列表为空");
            return Collections.emptyList();
        }
        List<SimpleUser> simpleUsers = userMapper.selectSimpleListByIds(followIds);
        for (SimpleUser simpleUser : simpleUsers) {
            String originalAvatarUrl = simpleUser.getAvatarUrl();
            String avatarUrl = minIOService.path2Link(originalAvatarUrl);
            simpleUser.setAvatarUrl(avatarUrl);
        }
        logger.info("selectFriends查询结果为 {}", simpleUsers);
        return simpleUsers;
    }

}
