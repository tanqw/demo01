package com.tan.controller;

import com.tan.api.CommonResult;
import com.tan.dao.AlterUsersParams;
import com.tan.dto.UserParams;
import com.tan.entity.UsersEntity;
import com.tan.service.impl.UserServiceImpl;
import com.tan.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UsersEntity> register(@RequestBody UserParams userParams) {
        UsersEntity users = userService.register(userParams);
        if (users == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(users);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UserParams userParams) {
        String token = userService.login(userParams);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Hello World";
    }

    @RequestMapping(value = "/alter", method = RequestMethod.POST)
    public CommonResult alter(@RequestBody AlterUsersParams alterUsersParams) {
        int count = userService.alterPassword(alterUsersParams);
        if (count == 0) {
            return CommonResult.failed();
        }
        return CommonResult.success("修改成功");
    }
}
