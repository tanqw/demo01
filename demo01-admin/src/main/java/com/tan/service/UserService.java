package com.tan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tan.dto.UserParams;
import com.tan.entity.UsersEntity;

import java.util.List;

public interface UserService extends IService<UsersEntity> {
    /**
     * 注册
     *
     * @param userParams
     * @return
     */
    UsersEntity register(UserParams userParams);

    /**
     * 查询是否已有账号
     *
     * @param account
     * @return
     */
    UsersEntity findUserByUserName(String account);

    /**
     * 登录
     *
     * @param userParams
     * @return
     */
    String login(UserParams userParams);

    /**
     * 获取权限
     *
     * @param id
     * @return
     */
    List<UsersEntity> getPermissionList(Integer id);
}
