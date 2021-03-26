package com.lzscofing.demoredis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisListTest extends RedisTest {

    @Test
    @Order(10)
    public void redisTemplateListOps() {
        String listTestKey = "listTestKey";
        List<String> arraysList = Arrays.asList("111", "222", "333", "333", "666", "777", "888");
        List<String> arrayList = new ArrayList<>(arraysList);
        Long pushAll = redisTemplate.opsForList().rightPushAll(listTestKey, arrayList);
        //在第一个333后面放上444
        Long push = redisTemplate.opsForList().rightPush(listTestKey, "333", "444");
        //从左侧去除一个元素
        Object leftPop = redisTemplate.opsForList().leftPop(listTestKey);
        Object index = redisTemplate.opsForList().index(listTestKey, 0);
        log.info("左侧元素 leftPop:{} , index为1 :{}", leftPop, index);
        List range = redisTemplate.opsForList().range(listTestKey, 0, 10);
        log.info("range 0- 10 :{}", JSONObject.toJSONString(range));
        //移除 3 个值为 333 的
        Long remove = redisTemplate.opsForList().remove(listTestKey, 3, "333");
        log.info("移除 3 个值为 333 的, 实际删除数量:{}", remove);
        List range2 = redisTemplate.opsForList().range(listTestKey, 0, 10);
        log.info("range 0- 10 :{}", JSONObject.toJSONString(range2));
        redisTemplate.opsForList().set(listTestKey, -1L, "0");
        List range3 = redisTemplate.opsForList().range(listTestKey, 0, 10);
        log.info("range 0- 10 :{}", JSONObject.toJSONString(range3));
    }
}
