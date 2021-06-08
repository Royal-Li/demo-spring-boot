package com.lzscoding.demobase.thread;

public class DeadLock implements Runnable {

    // flag=1，占有对象o1，等待对象o2
    // flag=0，占有对象o2，等待对象o1
    public int flag = 1;

    // 定义两个Object对象，模拟两个线程占有的资源
    public static Object o1 = new Object();
    public static Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();

        deadLock1.flag = 0;
        deadLock2.flag = 1;

        Thread thread1 = new Thread(deadLock1, "tom");
        Thread thread2 = new Thread(deadLock2, "jerry");

        thread1.start();
        Thread.sleep(1);
        thread2.start();

    }

    public void run() {

        System.out.println("flag: " + flag);

        // deadLock2占用资源o1，准备获取资源o2
        if (flag == 1) {
            while (true) {
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "拿到了资源1");
                    synchronized (o2) {
                        System.out.println(Thread.currentThread().getName() + "拿到了资源2");
                    }
                }
            }
        }

        // deadLock1占用资源o2，准备获取资源o1
        else if (flag == 0) {
            while (true) {
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "拿到了资源2");
                    synchronized (o1) {
                        System.out.println(Thread.currentThread().getName() + "拿到了资源1");
                    }
                }
            }
        }
    }
}
