package com.lzscofing.demoredis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 使用fastjson序列化redisSession 避免乱码使得value可读
 *
 * @author 180626
 */
@Slf4j
@Configuration
public class SessionConfig {

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        log.info("redisSession序列化成功");
        return new GenericFastJsonRedisSerializer();
//        return new GenericJackson2JsonRedisSerializer();
    }

}
