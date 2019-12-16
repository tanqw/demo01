package com.tan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tan.dao.AlterUsersParams;
import com.tan.entity.UsersEntity;

import java.util.List;

public interface UsersMapper extends BaseMapper<UsersEntity> {
    //获取当前用户角色信息
    UsersEntity getUserRoleRelation(String account);

    //获取当前用户角色权限
    List<UsersEntity> getPermissionList(Integer id);

    //修改账户密码
    int alterPassword(AlterUsersParams alterUsersParams);
}
