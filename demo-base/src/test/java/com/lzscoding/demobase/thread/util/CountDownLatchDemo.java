package com.lzscoding.demobase.thread.util;

import java.util.concurrent.CountDownLatch;

/**
 * 三个线程同时执行
 * CountDownLatch概念
 * <p>
 * CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。
 * <p>
 * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成一些任务，然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。
 * <p>
 * CountDownLatch的用法
 * <p>
 * CountDownLatch典型用法：1、某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为new CountDownLatch(n)，每当一个任务线程执行完毕，就将计数器减1 countdownLatch.countDown()，当计数器的值变为0时，在CountDownLatch上await()的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
 * <p>
 * CountDownLatch典型用法：2、实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计算器初始化为1，多个线程在开始执行任务前首先countdownlatch.await()，当主线程调用countDown()时，计数器变为0，多个线程同时被唤醒。
 * <p>
 * CountDownLatch的不足
 * <p>
 * CountDownLatch是一次性的，计算器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用。
 * <p>
 * CountDownLatch 基于 AQS 的共享模式的使用
 */
public class CountDownLatchDemo {
    public int count = 0;

    public void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 3;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "----------" + "准备!");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + "----------" + System.currentTimeMillis() + "出发!");
//                    countDownLatch.await();
//                    System.out.println("再次出发!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = size; i > 0; i--) {
            System.out.println(Thread.currentThread().getName() + "----------" + i + "秒钟之后 开始出发");
            Thread.sleep(1000);
            countDownLatch.countDown();
        }


    }
}
