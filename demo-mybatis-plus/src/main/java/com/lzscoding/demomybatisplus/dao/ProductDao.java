package com.lzscoding.demomybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzscoding.demomybatisplus.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductDao extends BaseMapper<Product> {
    int deleteByPrimaryKey(Integer id);

//    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //     悲观锁
//    @Select("select * from product where id = #{id} for update")
    @Select("select * from product where id = #{id} ")
    Product getProduct(@Param("id") Integer id);

    @Update("update product set stock=stock-1 where id =#{id}")
    int deductStock(@Param("id") Integer id);

}