package com.lzscoding.demo.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 180626
 */
@Data
@Configuration
public class TestBean implements BeanPostProcessor {

    private Integer id;
    private String name;
    private String beanName;

    public TestBean() {
        System.out.println("TestBean 实例化");
    }

    /**
     * 自定义初始化方法
     */
    public void initTestBean() {
        System.out.println("TestBean 中自定义的初始化方法");
    }

    /**
     * 自定义销毁方法
     */
    public void destroyTestBean() {
        System.out.println("TestBean 中自定义的销毁方法");
    }


    @Bean(initMethod = "initTestBean",destroyMethod = "destroyTestBean")
    public TestBean testBeanInjection(TestBean testBean){
        testBean.setBeanName("四大皆空");
        return testBean;
    }

}
