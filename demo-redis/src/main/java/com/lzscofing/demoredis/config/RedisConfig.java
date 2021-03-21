package com.lzscofing.demoredis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 * 1 实现redis key value 序列化
 *
 * @author 180626
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private String redisPort;
    @Value("${spring.redis.database}")
    private Integer redisDatabase;


    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
        return new GenericFastJsonRedisSerializer();
    }

    /**
     * key和hashKey: 推荐使用StringRedisSerializer: 简单的字符串序列化
     * hashValue: 推荐使用 GenericJackson2JsonRedisSerializer：类似Jackson2JsonRedisSerializer，但使用时构造函数不用特定的类。
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisTemplate Serializer(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer());
        log.info("redisTemplate序列化成功");
        return redisTemplate;
    }


    /**
     * 设置redission相关配置
     * @return
     */
    @Bean
    public Redisson redisson() {
        //此为单机模式
        Config redissionCfg = new Config();
        redissionCfg.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort).setDatabase(redisDatabase);
        return (Redisson) Redisson.create(redissionCfg);
    }
}
