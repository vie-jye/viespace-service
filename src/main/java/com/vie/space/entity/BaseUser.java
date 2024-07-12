package com.vie.space.entity;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("base_user")
public class BaseUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    private Long createTime;

    @TableField("create_user")
    private Long createUser;

    @TableField("update_time")
    private Long updateTime;

    @TableField("update_user")
    private Long updateUser;

    @TableField("is_del")
    private Integer isDel;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("session")
    private String session;
}