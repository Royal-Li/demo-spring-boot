package com.lzscoding.demobase.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadDemo4 {
    public static void main(String[] args) throws Exception {
        System.out.println("我是主线程:" + Thread.currentThread().getName());
        System.out.println(test2());

    }

    public static String test() throws Exception {

        Thread jerryThread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "  执行完毕");
            }
        }, "jerry");

        new Thread("tom") {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  执行完毕");
            }
        }.start();

        /**
         *
         */
        new Thread(() -> {
            jerryThread.start();
            try {
                jerryThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  执行完毕");
        }, "dog").start();

        return "test1返回值 " + Thread.currentThread().getName();
    }

    public static String test2() throws Exception {
        return "test2返回值" + test();
    }
}
