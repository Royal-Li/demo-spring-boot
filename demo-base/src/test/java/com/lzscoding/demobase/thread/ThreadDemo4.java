package com.lzscoding.demobase.thread;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        System.out.println("我是主线程:" + Thread.currentThread().getName());
        System.out.println(test2());

    }

    public static String test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  执行完毕");
            }
        }).start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  执行完毕");
            }
        }.start();
        return "test1返回值 " + Thread.currentThread().getName();
    }

    public static String test2() {
        return "test2返回值" + test();
    }
}
