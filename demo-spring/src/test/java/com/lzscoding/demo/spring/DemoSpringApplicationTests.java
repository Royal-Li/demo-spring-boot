package com.lzscoding.demo.spring;

import com.lzscoding.demo.spring.bean.TestBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class DemoSpringApplicationTests {

//    @Autowired
//    TestBean testBean;

    @Test
    void contextLoads() {
        System.out.println(111);
    }

//    @Test
//    public void test1() {
//        System.out.println(testBean.getBeanName());
//    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestBean.class);
        System.out.println("IOC容器创建完成....");
    }


}
