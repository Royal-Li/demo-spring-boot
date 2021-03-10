package com.lzscoding.dubbo.consumer.controller;

import com.lzscoding.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 180626
 */
@Slf4j
@RestController
public class HelloController {


    @Reference(version = "1.0.0")
    HelloService helloService;

    @RequestMapping("/hello")
    public String sayHello(String name) {
        log.info("向服务提供端发送消息");
        String result = helloService.sayHello(name);
        return result;
    }

}
