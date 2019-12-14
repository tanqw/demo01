package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录注册参数对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserParams {
    private String account;
    private String password;
}
