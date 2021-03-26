package com.lzscofing.demoredis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisZSetTest extends RedisTest {


    @Test
    @Order(11)
    public void redisTemplateZSetOps() {
        String zsetTestKey = "zsetTestKey";
        String zsetValue1 = "value1";

        redisTemplate.delete(zsetTestKey);

        Set<ZSetOperations.TypedTuple> randomSet = new HashSet<>();
        IntStream ints = new Random().ints(100, 100, 1000);
        ints.forEach(e -> {
                    randomSet.add(new DefaultTypedTuple("value" + e, Double.valueOf(e)));
                    System.out.println(e);
                }
        );
        Boolean add = redisTemplate.opsForZSet().add(zsetTestKey, zsetValue1, 99);
        Long add1 = redisTemplate.opsForZSet().add(zsetTestKey, randomSet);
        //按照排名(由小到大)打印元素 -1为全部
        Set range = redisTemplate.opsForZSet().range(zsetTestKey, 0, -1);
        Long size = redisTemplate.opsForZSet().size(zsetTestKey);
        log.info("-------总数:{} , 由小到大的顺序为---------", size);
        range.stream().forEach(e -> System.out.println(e));
        //获取指定元素的分数
        Double score = redisTemplate.opsForZSet().score(zsetTestKey, zsetValue1);
        log.info("{} : 的分数为 : {}", zsetValue1, score);
        //返回900-1000指定范围的成员个数
        Long count = redisTemplate.opsForZSet().count(zsetTestKey, 900, 1000);
        log.info("900-1000的成员个数为:{}", count);
        //返回成员的排名
        Long rank = redisTemplate.opsForZSet().rank(zsetTestKey, zsetValue1);
        Long reverseRank = redisTemplate.opsForZSet().reverseRank(zsetTestKey, zsetValue1);
        log.info("{}由小到大的排名是:{} \t {}由da到小的排名是:{}", zsetValue1, rank, zsetValue1, reverseRank);
        //为指定元素加分
        Double incrementScore = redisTemplate.opsForZSet().incrementScore(zsetTestKey, zsetValue1, 0.99);
        log.info("{}:加分后的分数:{}", zsetValue1, incrementScore);
        //删除指定索引范围的元素
        Long removeRangeResult = redisTemplate.opsForZSet().removeRange(zsetTestKey, 1, 10);
        //删除指定分数范围的元素
        Long removeRangeByScoreResult = redisTemplate.opsForZSet().removeRangeByScore(zsetTestKey, 500, 600);

    }
}
