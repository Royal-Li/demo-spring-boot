package com.lzscoding.demobase.thread.volatilee;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VolatileTest {

    private static Integer NumberNoShare = 0;
    private static volatile Integer NumberShare = 0;


    /**
     * Thread.sleep ，println 都会影响到 当前 线程 将副本与主存同步
     *
     * @param args
     */

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //开10个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            log.info("NumberShare开始阻塞 5秒后释放");
            while (NumberShare == 0) {
            }
            log.info("NumberShare阻塞已释放");
        });
        executorService2.execute(() -> {
            log.info("NumberNoShare开始阻塞 5秒后无法释放");
            while (NumberNoShare == 0) {
            }
            log.info("NumberNoShare阻塞已释放");
        });

        sleep(5000);

        new Thread(() -> {
            log.info("NumberNoShare 变为 1 释放阻塞");
            NumberNoShare = 1;
            log.info("NumberShare 变为 1 释放阻塞");
            NumberShare = 1;
        }).start();

        executorService.shutdown();
        executorService2.shutdown();
    }


    //线程休眠
    private static void sleep(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
