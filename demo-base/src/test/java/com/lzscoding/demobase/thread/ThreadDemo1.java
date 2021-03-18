package com.lzscoding.demobase.thread;

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("当前线程为:" + Thread.currentThread().getName());
    }
}

public class ThreadDemo1 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MyThread myThread = new MyThread();
        myThread.run();//run是方法调用 还是主线程
        myThread.start();//start才是启动线程
        System.out.println("执行时间:" + (System.currentTimeMillis() - start));
        new Thread().start();

    }
}
