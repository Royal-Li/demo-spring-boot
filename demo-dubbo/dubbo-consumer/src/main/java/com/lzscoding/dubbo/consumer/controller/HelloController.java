package com.lzscoding.dubbo.consumer.controller;

import com.lzscoding.dubbo.common.service.ErrorService;
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


    @Reference(version = "1.0.0", check = false)
    HelloService helloService;
    @Reference(version = "2.0.0", check = false)
    HelloService helloServiceVersion2;
    @Reference(version = "1.0.0", check = false)
    ErrorService errorService;

    @RequestMapping("/hello")
    public String sayHello(String name) {
        log.info("向服务提供端发送消息");
        String result = helloService.sayHello(name);
        log.info(result);
        return result;
    }

    @RequestMapping("/hello2")
    public String sayHelloVersion2(String name) {
        log.info("2.0向服务提供端发送消息");
        String result = helloServiceVersion2.sayHello(name);
        log.info(result);
        return result;
    }

    @RequestMapping("/timeout")
    public String timeOut(String str) {
        log.error("此请求会超时,并且远程调用service   4次");
        return errorService.outTime(str);
    }

    @RequestMapping("/err")
    public String err(String str) {
        log.error("服务端--->此请求会出错");
        String result = null;
        try {
            result = errorService.err(str);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务端--->捕捉到请求出错啦!!!");
        }
        return result;
    }


}
