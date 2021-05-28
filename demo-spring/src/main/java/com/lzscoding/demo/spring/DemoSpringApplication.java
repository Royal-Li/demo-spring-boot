package com.lzscoding.demo.spring;

import com.lzscoding.demo.spring.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @author 180626
 */
@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication.class, args);
        System.out.println("项目已启动");

    }


//    @Lazy
//    @Bean(initMethod = "initTestBean",destroyMethod = "destroyTestBean")
//    public TestBean testBeanInjection(TestBean testBean){
//        testBean.setBeanName("四大皆空");
//        return testBean;
//    }

}
