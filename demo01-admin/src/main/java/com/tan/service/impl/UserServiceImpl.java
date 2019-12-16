package com.tan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tan.dao.AlterUsersParams;
import com.tan.dto.UserParams;
import com.tan.entity.UsersEntity;
import com.tan.mapper.UsersMapper;
import com.tan.service.UserService;
import com.tan.utils.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsService userDetailsService;

    public UsersEntity register(UserParams userParams) {
        UsersEntity usersEntity = new UsersEntity();
        if (findUserByUserName(userParams.getAccount()) != null) {
            return null;
        }
        BeanUtils.copyProperties(userParams, usersEntity);
        String password = passwordEncoder.encode(usersEntity.getPassword());
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(userParams.getAccount());
        if (!passwordEncoder.matches(userParams.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public List<UsersEntity> getPermissionList(Integer id) {
        return usersMapper.getPermissionList(id);
    }

    @Override
    public int alterPassword(AlterUsersParams alterUsersParams) {
        String password = passwordEncoder.encode(alterUsersParams.getPassword());
        alterUsersParams.setPassword(password);
        return usersMapper.alterPassword(alterUsersParams);
    }

}
