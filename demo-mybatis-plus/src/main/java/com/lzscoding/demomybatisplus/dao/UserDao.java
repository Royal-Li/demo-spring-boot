package com.lzscoding.demomybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzscoding.demomybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    int deleteByPrimaryKey(Long id);

//    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("Select * from user where age = 20")
    List<User> selectAge20();

    List<User> selectByCondition(User user);
}