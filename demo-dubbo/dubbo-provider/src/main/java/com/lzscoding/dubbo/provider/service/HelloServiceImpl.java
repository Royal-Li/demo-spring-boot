package com.lzscoding.dubbo.provider.service;

import com.lzscoding.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 180626
 */

@Slf4j
@Service(version = "1.0.0", interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {
    /**
     * @param name 姓名
     * @return 问好
     */
    @Override
    public String sayHello(String name) {
        log.info("程序提供方接收到调用请求");
        return "我是provider返回的name: " + name;
    }
}
