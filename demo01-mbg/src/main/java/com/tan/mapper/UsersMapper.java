package com.tan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tan.entity.UserEntity;

import java.util.List;

public interface UsersMapper extends BaseMapper<UserEntity> {
    //获取当前用户角色信息
    List<UserEntity> getUserRoleRelation(Long usersId);
}
