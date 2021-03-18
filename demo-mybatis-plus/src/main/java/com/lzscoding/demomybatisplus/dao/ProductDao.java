package com.lzscoding.demomybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzscoding.demomybatisplus.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao extends BaseMapper<Product> {
    int deleteByPrimaryKey(Integer id);

//    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}