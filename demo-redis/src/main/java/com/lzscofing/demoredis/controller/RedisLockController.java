package com.lzscofing.demoredis.controller;

import com.lzscofing.demoredis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁实验
 *
 * @author 180626
 */
@Slf4j
@RestController
public class RedisLockController {

    private static final String LOCK_KEY = "lockKey_product_1";

    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Redisson redisson;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/port")
    public HttpEntity<?> testPort() {
        return new ResponseEntity<>(port, HttpStatus.OK);
    }


    /**
     * 不加锁直接生成订单
     *
     * @return
     */
    @RequestMapping("/goodskill/nolock")
    public HttpEntity<?> goodsKillNoLock() {
        try {
            //秒杀商品库里id为1的商品
            orderService.createOrder(1, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new ResponseEntity<>("下单失败,发生错误", HttpStatus.OK);
        }
        return new ResponseEntity<>("下单成功", HttpStatus.OK);
    }


    /**
     * 手动加锁生成订单
     * 这样的锁不好  应该有个排队机制  而不是没抢到锁直接返回
     *
     * @return
     */
    @RequestMapping("/goodskill/lock")
    public HttpEntity<?> goodsKillLock() {
        ////加上uuid主要为了区分锁 避免别的请求进来 得不到锁后把你的锁给删掉
        String LOCK_VALUE = "lockValue" + UUID.randomUUID().toString();

        try {
            //加上锁持续时间,避免程序意外宕机,锁不释放
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY, LOCK_VALUE, 10, TimeUnit.SECONDS);
            if (!result) {
                return new ResponseEntity<>("未得到锁 请求繁忙", HttpStatus.OK);
            }
            //秒杀商品库里id为1的商品
            orderService.createOrder(1, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new ResponseEntity<>("下单失败,发生错误", HttpStatus.OK);
        } finally {
            //这里不能直接删除锁 需要先判断是不是自己家的锁 再去删除 避免其他进程将你的锁删掉
            this.unLock(LOCK_KEY, LOCK_VALUE);
        }
        return new ResponseEntity<>("下单成功", HttpStatus.OK);
    }

    @RequestMapping("/goodskill/lock/redission")
    public HttpEntity<?> goodsKillLockRedission() {
        //获取锁对象
        RLock redissionLock = redisson.getLock(LOCK_KEY);
        try {
            //加锁
            redissionLock.lock(10, TimeUnit.SECONDS);
            //秒杀商品库里id为1的商品
            orderService.createOrder(1, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            new ResponseEntity<>("下单失败,发生错误", HttpStatus.OK);
        } finally {
            //释放锁
            redissionLock.unlock();
        }
        return new ResponseEntity<>("下单成功", HttpStatus.OK);
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param lockValue
     * @return
     */
    private Boolean unLock(String lockKey, String lockValue) {
        String result = stringRedisTemplate.opsForValue().get(lockKey);
        if (StringUtils.isNotEmpty(result)) {
            if (result.equals(lockValue)) {
                stringRedisTemplate.delete(lockKey);
                return true;
            }
        }
        return false;
    }


}
