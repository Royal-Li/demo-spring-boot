package com.lzscoding.dubbo.provider.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBean {
    @Bean
    public void bean1(){
        Protocol myProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myProtocol");
        System.out.println(myProtocol.getDefaultPort());
        Protocol redis = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("redis");
        System.out.println(redis.getDefaultPort());
    }
}
