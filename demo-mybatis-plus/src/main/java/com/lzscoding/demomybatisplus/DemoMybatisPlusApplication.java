package com.lzscoding.demomybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 180626
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.lzscoding.demomybatisplus.dao"})
public class DemoMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisPlusApplication.class, args);
    }

}
