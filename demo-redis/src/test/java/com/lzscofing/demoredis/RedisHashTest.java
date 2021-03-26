package com.lzscofing.demoredis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.data.redis.core.BoundHashOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisHashTest extends RedisTest{

    @Test
    @Order(8)
    public void redisTemplateHashOps() {
        BoundHashOperations hashTestOperations = redisTemplate.boundHashOps("hashTestKey");
        Boolean expire = hashTestOperations.expire(1, TimeUnit.HOURS);
        log.info("设置过期时间{}", expire ? "成功" : "失败");
        hashTestOperations.put(redisKey, redisKeyValue);
        Map<String, String> keyValues = new HashMap<>(16);
        IntStream.range(1, 10).forEach(i -> keyValues.put("hashField" + i, "hashValue" + i));
        hashTestOperations.putAll(keyValues);
        Set hashFields = hashTestOperations.keys();
        log.info("hashFields:{}", JSONObject.toJSONString(hashFields));
        List hashValues = hashTestOperations.values();
        log.info("hashValues:{}", JSONObject.toJSONString(hashValues));
        Map entries = hashTestOperations.entries();
        log.info("entries:{}", JSONObject.toJSONString(entries));

        String redisKeyValue = (String) redisTemplate.opsForHash().get("hashTestKey", redisKey);
        log.info("hashTest->field:{},fieldValue:{}", redisKey, redisKeyValue);
        Boolean hasKeyBefore = redisTemplate.opsForHash().hasKey("hashTestKey", redisKey);
        redisTemplate.opsForHash().delete("hashTestKey", redisKey);
        Boolean hasKeyAfter = redisTemplate.opsForHash().hasKey("hashTestKey", redisKey);
        log.info("删除hashTest->field:{} 前 hasKey:{} ? {} , 后 hasKey:{} ? {} ", redisKey, redisKey, hasKeyBefore, redisKey, hasKeyAfter);

    }
}
