package com.lzscoding.demozookeeper.mapper;


import com.lzscoding.demozookeeper.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderDao {

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into `order` ( user_id, product_id) value ( #{userId}, #{productId} )")
    int insert(Order order);


}