package com.vie.space.entity;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("base_article")
public class BaseArticle implements Serializable {
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

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("content")
    private String content;

    @TableField("article_type")
    private String articleType;

    @TableField("tags")
    private String tags;

    @TableField("category_id")
    private Long categoryId;

    @TableField("category")
    private String category;

    @TableField("views")
    private Long views;

    @TableField("image")
    private String image;

    @TableField("status")
    private Integer status;

    @TableField("sort_order")
    private Integer sortOrder;
}