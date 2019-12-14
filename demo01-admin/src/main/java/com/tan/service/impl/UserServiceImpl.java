package com.tan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tan.dto.UserParams;
import com.tan.entity.UsersEntity;
import com.tan.mapper.UsersMapper;
import com.tan.service.UserService;
import com.tan.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    MD5Util md5Util;

    public UsersEntity register(UserParams userParams) {
        UsersEntity usersEntity = new UsersEntity();
        if (findUserByUserName(userParams.getAccount()) != null) {
            return null;
        }
        BeanUtils.copyProperties(userParams, usersEntity);
        String password = md5Util.setPwd(usersEntity.getPassword());
        usersEntity.setPassword(password);
        usersEntity.setAge(19);
        usersEntity.setName("tan");
        usersEntity.setSex("男");
        usersEntity.setStatus(true);
        int count = usersMapper.insert(usersEntity);
        if (count == 0) {
            return null;
        }
        return usersEntity;
    }

    @Override
    public UsersEntity findUserByUserName(String account) {
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<UsersEntity>();
        queryWrapper.eq("account", account);
        UsersEntity usersEntity = usersMapper.selectOne(queryWrapper);
        return usersEntity;
    }

    @Override
    public String login(UserParams userParams) {
        String token = null;
        UsersEntity usersEntities = usersMapper.getUserRoleRelation(userParams.getAccount());
        if (!md5Util.Check(userParams.getPassword(), usersEntities.getPassword())) {
            return null;
        }
        token = "success";
        return token;
    }
}
