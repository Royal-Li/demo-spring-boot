package com.lzscoding.demomybatisplus;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//让测试运行于Spring测试环境，以便在测试开始的时候自动创建Spring的应用上下文
//@RunWith(SpringRunner.class)
public class DemoMybatisPlusApplicationTests {

    @Test
    void contextLoads() {
    }

}
