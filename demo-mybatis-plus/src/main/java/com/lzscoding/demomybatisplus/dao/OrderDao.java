package com.lzscoding.demomybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzscoding.demomybatisplus.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<Order> {
    int deleteByPrimaryKey(Integer id);

//    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}