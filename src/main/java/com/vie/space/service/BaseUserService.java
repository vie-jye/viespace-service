package com.vie.space.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vie.space.entity.BaseUser;

public interface BaseUserService extends IService<BaseUser> {

    BaseUser selectById(long id);

    BaseUser selectByUsername(String username);

    BaseUser selectByUsernameAndPassword(String username, String password);

    BaseUser selectBySession(String session);

    List<BaseUser> selectAll();

    int insert(BaseUser user);

    int updatePasswordByUsername(BaseUser user);

    int updateSessionById(BaseUser user);

    int deleteByUsername(String username);
}