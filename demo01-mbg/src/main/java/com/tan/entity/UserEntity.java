package com.tan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String account;
    private String password;
    private Boolean status;
}
