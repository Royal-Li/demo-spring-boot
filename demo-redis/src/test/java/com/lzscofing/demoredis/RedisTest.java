package com.lzscofing.demoredis;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * junit-vintage-engine   是 JUnit 4 中使用的测试引擎。
 * <p>
 * junit-jupiter-engine   是 JUnit 5 中使用的测试引擎。
 * <p>
 * 如果你的 Spring 项目使用的新的 Spring Boot 版本的话，你应该默认使用了 JUnit 5 的引擎，
 * 因此为了兼容性，你需要在 spring-boot-starter-test 这个 POM 引用的时候将 JUnit 4 的引擎去除掉。
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisTest extends DemoRedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String, Serializable> serializableRedisTemplate;

    String redisKey = "Today";
    String redisKeyValue = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

    @Test
    @Order(1)
    public void test1() {
        Long start = System.currentTimeMillis();
        System.out.println("hello word");
        Long end = System.currentTimeMillis();
        log.info("redis测试类 ---------- > 执行时间:{}", start - end);
    }

    @Test
    @Order(2)
    public void redisTemplateSet() {
        log.info("redis设置key:{},value:{}", redisKey, redisKeyValue);
        redisTemplate.opsForValue().set(redisKey, redisKeyValue, 60, TimeUnit.SECONDS);
        log.info("redis设置key1:{},value1:{}", redisKey + "1", redisKeyValue);
        redisTemplate.opsForValue().set(redisKey + "1", redisKeyValue, 60, TimeUnit.SECONDS);
    }

    @Test
    @Order(3)
    public void redisTemplateExpire() {
        Boolean result = redisTemplate.expire(redisKey, 1, TimeUnit.MINUTES);
        if (result) {
            Long expire = redisTemplate.getExpire(redisKey);
            log.info("设置key:{}过期时间为:{}", redisKey, expire);
            return;
        }
        log.warn("设置过期时间失败");
    }

    @Test
    @Order(4)
    public void redisTemplateHasKey() {
        Boolean result = redisTemplate.hasKey(redisKey);
        log.info("查询是否有key:{},结果:{}", redisKey, result);
    }

    @Test
    @Order(5)
    public void redisTemplateDelete() {
        List<String> arraysList = Arrays.asList(redisKey, redisKey + "1");
        ArrayList<String> arrayList = new ArrayList<>(arraysList);
        Long delete = redisTemplate.delete(arrayList);
        log.info("删除结果:{}", delete);
    }

    @Test
    @Order(6)
    public void redisTemplateIncrement() {
        log.warn("验证redis原子自增");
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        IntStream.range(0, 10000).forEach(i -> executorService.execute(() -> redisTemplate.opsForValue().increment("count", 1)));
        log.warn("线程安全的值为 : {}", stringRedisTemplate.opsForValue().get("count"));
    }

    @Test
    @Order(7)
    public void redisTemplateBoundValueOps() {
        Object count = redisTemplate.boundValueOps("count").get();
        Object count1 = redisTemplate.opsForValue().get("count");
        log.info("increment count的值为:{},{}", count.toString(), count1.toString());
    }




}
