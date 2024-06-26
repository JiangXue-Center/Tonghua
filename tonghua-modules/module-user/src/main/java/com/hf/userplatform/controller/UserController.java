package com.hf.userplatform.controller;


import com.hf.core.model.Result;
import com.hf.core.model.entity.user.User;
import com.hf.core.model.vo.SimpleUser;
import com.hf.userplatform.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/base/{userId}")
    public Map<String, String> getUserBaseInfoByUserId(@PathVariable("userId") String userId) {
        return userService.selectUserBaseInfo(userId);
    }

    @GetMapping("/email/{email}")
    public User getInfoByEmail(@PathVariable("email") String email) {
        User user = userService.selectUserByEmail(email);
        return user;
    }

    @GetMapping("/phone/{phone}")
    public User getInfoByPhone(@PathVariable("phone") String phone) {
        User user = userService.selectUserByPhone(phone);
        return user;
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody User user) {
        Boolean b = userService.register(user);
        return b;
    }

    @PutMapping("/email/{email}")
    public Result<Map<String, String>> bindEmail(@PathVariable("email") String email,
                                                 @RequestParam String code) {
        return Result.success(userService.bindEmail(email, code));
    }

    @PutMapping("/phone/{phone}")
    public Result<Map<String, String>> bindPhone(@PathVariable("phone") String phone,
                                                 @RequestParam String code) {
        Map<String, String> map = userService.bindPhone(phone, code);
        return Result.success(map);
    }

    @PutMapping("/fans/{userId}")
    public Result<String> follow(@PathVariable("userId") String userId) {
        String result = userService.followUser(userId);
        return Result.success(result);
    }

    @PostMapping("/simpleList")
    public List<SimpleUser> selectSimpleUser(@RequestBody Set<String> userIds) {
        return userService.selectSimpleUsers(userIds);
    }

    //
    @GetMapping("/follows")
    public Result<List<SimpleUser>> selectFollows(HttpServletRequest request) {
        return Result.success(userService.selectFriends(request.getServletPath()));
    }

    //查询好友列表
    @GetMapping("/fans")
    public Result<List<SimpleUser>> selectFans(HttpServletRequest request) {
        return Result.success(userService.selectFriends(request.getServletPath()));
    }


}
