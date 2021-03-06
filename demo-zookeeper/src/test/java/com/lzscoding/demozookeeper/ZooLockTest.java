package com.lzscoding.demozookeeper;


import com.lzscoding.demozookeeper.annotation.ZooLock;
import com.lzscoding.demozookeeper.aspectj.ZooLockAspect;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * zooLockTest 需要跟DemoZookeeperApplicationTests 一个目录下
 * 否则需要将DemoZookeeperApplicationTests 变成public
 * <p>
 * 如果不继承DemoZookeeperApplicationTests autowrited CuratorFramework zkClient 为空
 */
@Slf4j
public class ZooLockTest extends DemoZookeeperApplicationTests {

    //定义库存数量
    private Integer count = 10000;

    public Integer getCount() {
        return count;
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(1000);
    @Autowired
    private CuratorFramework zkClient;


    /**
     * 测试方法不能私有也不能有返回值 否则会报 no tests were found
     * 不使用分布式锁,程序结束查看count的值是否为0
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        IntStream.range(1, 10000).forEach(i -> executorService.execute(this::doBuy));
        TimeUnit.SECONDS.sleep(3);
        log.error("库存的count值为:{}", count);
    }

    /**
     * 测试AOP分布式锁
     */
    @Test
    public void testAopLock() throws InterruptedException {
        // 测试类中使用AOP需要手动代理
        ZooLockTest target = new ZooLockTest();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ZooLockAspect aspect = new ZooLockAspect(zkClient);
        factory.addAspect(aspect);
        ZooLockTest proxy = factory.getProxy();
        // 模拟并发  1000个线程买1w次商品
        IntStream.range(0, 10000).forEach(i -> executorService.execute(() -> proxy.aopBuy(i)));
        //模拟后台处理时间 2分钟,如果不加处理时间test测试会直接结束
        TimeUnit.MINUTES.sleep(2);
        log.error("count值为{}", proxy.getCount());
    }

    @ZooLock(key = "buy", timeout = 1, timeUnit = TimeUnit.MINUTES)
    public void aopBuy(int userId) {
        log.info("{} 正在出库 ...... ", userId);
        doBuy();
        log.info("{} 扣库存成功 ...... ", userId);
    }

    /**
     * 测试手动加锁
     *
     * @throws InterruptedException
     */
    @Test
    public void testManualLock() throws InterruptedException {
        IntStream.range(1, 10000).forEach(i -> executorService.execute(this::manualBuy));
        TimeUnit.MINUTES.sleep(1);
        log.error("count的值为:{}", count);
    }

    public void manualBuy() {
        String lockPath = "/buy";
        log.info("try to buy sth .");
        try {
            InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
            try {
                boolean lockSuccess = lock.acquire(1, TimeUnit.MINUTES);
                if (lockSuccess) {
                    doBuy();
                    log.info("buy successfully!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        } catch (Exception e) {
            log.error("zk error {}", e);
        }
    }


    public void doBuy() {
        count--;
        log.info("count值为{}", count);
    }


}
