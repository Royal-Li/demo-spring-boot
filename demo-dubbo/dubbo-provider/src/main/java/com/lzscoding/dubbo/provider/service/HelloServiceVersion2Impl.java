package com.lzscoding.dubbo.provider.service;

import com.lzscoding.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

@Slf4j
@Service(version = "2.0.0", interfaceClass = HelloService.class)
public class HelloServiceVersion2Impl implements HelloService {
    @Override
    public String sayHello(String name) {
        log.info("程序提供方接收到调用请求");
        return "我是provider返回的name: " + name + "  version 2.0.0";
    }
}
