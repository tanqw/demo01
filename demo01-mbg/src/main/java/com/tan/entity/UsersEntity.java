package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("account")
public class UsersEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String account;
    private String password;
    private Boolean status;
}
