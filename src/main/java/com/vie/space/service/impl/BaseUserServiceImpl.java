// FILEPATH: /cloudide/workspace/Java/src/main/java/com/vie/space/service/impl/BaseUserServiceImpl.java
package com.vie.space.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vie.space.core.util.MD5Util;
import com.vie.space.entity.BaseUser;
import com.vie.space.mapper.BaseUserMapper;
import com.vie.space.service.BaseUserService;

@Service("baseUserService")
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public BaseUser selectById(long id) {
        return baseUserMapper.selectById(id);
    }

    @Override
    public BaseUser selectByUsername(String username) {
        return baseUserMapper.selectByUsername(username);
    }

    @Override
    public BaseUser selectByUsernameAndPassword(String username, String password) {
        return baseUserMapper.selectByUsernameAndPassword(username, MD5Util.md5(password));
    }

    @Override
    public BaseUser selectBySession(String session) {
        return baseUserMapper.selectBySession(session);
    }

    @Override
    public List<BaseUser> selectAll() {
        return baseUserMapper.selectAll();
    }

    @Override
    public int insert(BaseUser user) {
        return baseUserMapper.insert(user);
    }

    @Override
    public int updatePasswordByUsername(BaseUser user) {
        return baseUserMapper.updatePasswordByUsername(user);
    }

    @Override
    public int deleteByUsername(String username) {
        return baseUserMapper.deleteByUsername(username);
    }

    @Override
    public int updateSessionById(BaseUser user) {
        return baseUserMapper.updateSessionById(user);
    }
}