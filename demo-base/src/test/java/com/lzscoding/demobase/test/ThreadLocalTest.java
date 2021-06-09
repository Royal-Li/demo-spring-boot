package com.lzscoding.demobase.test;

public class ThreadLocalTest {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set(Thread.currentThread().getName());
        System.out.println(threadLocal.get());

        new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(threadLocal.get());


            ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
            //这时 threadlocalMap 的大小变为2
            threadLocal2.set(2);
            System.out.println(threadLocal2.get());

        }).start();

    }
}
