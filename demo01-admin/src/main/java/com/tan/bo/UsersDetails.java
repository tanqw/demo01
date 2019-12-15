package com.tan.bo;

import com.tan.entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author chengcheng
 * @Date 2019/12/6 9:43
 * @Description ：UserDetails
 * @Version 1.0
 **/
public class UsersDetails implements UserDetails {

    private UsersEntity usersEntity;
    private List<UsersEntity> permissionEntity;

    public UsersDetails(UsersEntity usersEntity, List<UsersEntity> permissionEntity) {
        this.usersEntity = usersEntity;
        this.permissionEntity = permissionEntity;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionEntity.stream().filter(permission -> permission.getName()!=null)
                .map(permission->new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usersEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return usersEntity.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usersEntity.getStatus().equals(1);
    }
}
