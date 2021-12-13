package com.lzscoding.demobase.thread.pool;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadPoolTest2 {
    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        IntStream.range(0, 20).forEach(i -> {
            newFixedThreadPool.execute(() -> {
                String random = null;
                try {
                    random = sleep2sReturnRandom();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (random.startsWith("a")) {
                    System.out.println("以a开头");
                }
                System.out.println(Thread.currentThread().getName() + "-----" + random);
            });
        });
        System.out.println("主线程继续执行");
        System.out.println(Thread.currentThread().getName());


    }

    public static String sleep2sReturnRandom() throws InterruptedException {

        Thread.sleep(2000);

        return UUID.randomUUID().toString();
    }
}
