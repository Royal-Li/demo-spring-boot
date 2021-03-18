package com.lzscoding.demobase.thread.pool;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();//占用cpu
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);//占用内存
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();//占用内存

        ExecutorService executorService3 = new ThreadPoolExecutor(10, 20,
                0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10)); //拒绝策略

        IntStream.range(0, 100).forEach(i -> {
            executorService3.execute(new MyTask(i));
//            executorService3.submit(new MyTask(i));

        });
    }
}

class MyTask implements Runnable {

    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---" + i);
        Thread.sleep(1000);//业务逻辑
    }
}