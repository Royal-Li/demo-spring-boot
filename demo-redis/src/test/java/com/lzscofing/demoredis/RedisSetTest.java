package com.lzscofing.demoredis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisSetTest extends RedisTest {

    @Test
    @Order(9)
    public void redisTemplateSetOps() {
        String setTestKey = "setTestKey";
        Long setAdd = redisTemplate.boundSetOps(setTestKey).add("setValue1", "setValue1", "setValue2");
        Long setAdd2 = redisTemplate.opsForSet().add(setTestKey, "setValue666", "setValue777");
//        redisTemplate.opsForSet().
        log.info("添加结果一成功数:{},添加结果二成功数:{}", setAdd, setAdd2);
        Boolean expire = redisTemplate.expire(setTestKey, 1, TimeUnit.HOURS);
        log.info("设置过期时间{}", expire ? "成功" : "失败");
        Set set = redisTemplate.opsForSet().members(setTestKey);
        Long size = redisTemplate.opsForSet().size(setTestKey);
        log.info("set大小:{},值:{}", size, JSONObject.toJSONString(set));
        Boolean isMember = redisTemplate.opsForSet().isMember(setTestKey, "setValue1");
        log.info("set中存在:{} ? {}", "setValue1", isMember);
        Long remove = redisTemplate.opsForSet().remove(setTestKey, "setValue1", "setValue2");
        log.info("移除:{} ? {} , set中存在:{} ? {}", "setValue1", remove > 0 ? true : false, "setValue1", redisTemplate.opsForSet().isMember(setTestKey, "setValue1"));
    }

    @Test
    public void redisTemplateSetOps2() {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        IntStream.range(0, 100).forEach(i -> {
            if (i < 60)
                set1.add("key" + i);
            if (i > 40)
                set2.add("key" + i);
        });

    }
}
