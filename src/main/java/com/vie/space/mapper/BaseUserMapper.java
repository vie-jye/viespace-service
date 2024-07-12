package com.vie.space.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vie.space.entity.BaseUser;

public interface BaseUserMapper extends BaseMapper<BaseUser> {

    @Select("SELECT * FROM base_user WHERE id = #{id}")
    BaseUser selectById(long id);

    @Select("SELECT * FROM base_user WHERE username = #{username}")
    BaseUser selectByUsername(String username);

    @Select("SELECT * FROM base_user WHERE username = #{username} AND password = #{password}")
    BaseUser selectByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM base_user WHERE session = #{session} limit 1")
    BaseUser selectBySession(String session);

    @Insert("INSERT INTO base_user (username, password) VALUES (#{username}, #{password})")
    int insert(BaseUser user);

    @Update("UPDATE base_user SET password = #{password} WHERE username = #{username}")
    int updatePasswordByUsername(BaseUser user);

    @Update("UPDATE base_user SET session = #{session} WHERE id = #{id}")
    int updateSessionById(BaseUser user);

    @Delete("DELETE FROM base_user WHERE username = #{username}")
    int deleteByUsername(String username);

    @Select("SELECT * FROM base_user")
    List<BaseUser> selectAll();
}