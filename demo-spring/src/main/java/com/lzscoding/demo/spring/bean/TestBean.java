package com.lzscoding.demo.spring.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring对bean进行实例化；
 * Spring将值和bean的引用注入到bean对应的属性中；
 * 如果bean实现了BeanNameAware接口，Spring将bean的ID传递给setBean-Name()方法；
 * 如果bean实现了BeanFactoryAware接口，Spring将调用setBeanFactory()方法，将BeanFactory
 * 容器实例传入；
 * 如果bean实现了ApplicationContextAware接口，Spring将调用setApplicationContext()方法，将
 * bean所在的应用上下文的引用传入进来；
 * 如果bean实现了BeanPostProcessor接口，Spring将调用它们的post�ProcessBeforeInitialization()方法；
 * 如果bean实现了InitializingBean接口，Spring将调用它们的after-PropertiesSet()方法。类似地，
 * 如果bean使用initmethod声明了初始化方法，该方法也会被调用；
 * 如果bean实现了BeanPostProcessor接口，Spring将调用它们的post-ProcessAfterInitialization()
 * 方法；
 * 此时，bean已经准备就绪，可以被应用程序使用了，它们将一直驻留在应用上下文中，直到该应
 * 用上下文被销毁；
 * 如果bean实现了DisposableBean接口，Spring将调用它的destroy()接口方法。同样，如果bean
 * 使用destroy-method声明了销毁方法，该方法也会被调用。
 *
 * @author 180626
 */
@Data
@Slf4j
@Configuration
public class TestBean implements InitializingBean {

    private Integer id;
    private String name;
    private String beanName;
    private String before;
    private String after;

    public TestBean() {
        log.info("TestBean 实例化");
    }

    public void setBeanName(String beanName) {
        log.info("TestBean set方法设置属性");
        this.beanName = beanName;
    }

    @Bean(initMethod = "initTestBean", destroyMethod = "destroyTestBean")
    public TestBean testBeanInjection(TestBean testBean) {
        log.info("设置bean属性并注入");
        testBean.setBeanName("我是beanName");
        return testBean;
    }

    public void initTestBean() {
        log.info("TestBean 中自定义的初始化方法initTestBean");
    }

    public void destroyTestBean() {
        log.info("TestBean 中自定义的销毁方法destroyTestBean");
    }

    @PostConstruct
    public void PostConstruct() {
        log.info("TestBean 中自定义的初始化方法PostConstruct");
    }

    @PreDestroy
    public void PreDestroy() {
        log.info("TestBean 中自定义的销毁方法PreDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("设置属性之后触发afterPropertiesSet");
    }
}
