package com.lzscoding.demozookeeper.mapper;


import com.lzscoding.demozookeeper.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductDao {
//     悲观锁
//    @Select("select * from product where id = #{id} for update")
    @Select("select * from product where id = #{id} ")
    Product getProduct(@Param("id") Integer id);

    @Update("update product set stock=stock-1 where id =#{id}")
    int deductStock(@Param("id") Integer id);

}
