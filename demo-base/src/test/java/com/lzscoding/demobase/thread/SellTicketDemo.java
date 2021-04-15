package com.lzscoding.demobase.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 不加锁卖票
 */
class SellTicketThread extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    //切换线程
                    Thread.sleep(0);
                    //模拟卖票处理时间
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出第" + ticket + "张票,剩余票数----->>>" + --ticket);
            } else {
                break;
            }
        }
    }
}

/**
 * synchronized关键字加锁卖票
 */
class SellTicketThreadLock extends Thread {
    private static int ticket = 100;
    private static Object object = new Object();

    @Override
    public void run() {
        System.out.println("这里synchronized 不能用this 不是一把锁 ->>" + this);
        while (true) {
            synchronized (object) {
                if (ticket > 0) {
                    try {
                        //切换线程
                        Thread.sleep(0);
                        //模拟卖票处理时间
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖出第" + ticket + "张票,剩余票数----->>>" + --ticket);
                } else {
                    break;
                }
            }
        }
    }
}

class SellTicketThreadReentrantLock extends Thread {
    private static int ticket = 100;
    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (ticket > 0) {
                try {
                    //切换线程
                    Thread.sleep(0);
                    //模拟卖票处理时间
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出第" + ticket + "张票,剩余票数----->>>" + --ticket);
            }
            lock.unlock();
        }
    }
}


public class SellTicketDemo {
    public static void main(String[] args) {

        //IntStream.range(0, 10).forEach(i -> new SellTicketThread().start());

        //如果加了synchronize关键字还锁不住 应该查找一下 是不是加的同一吧锁
        IntStream.range(0, 5).forEach(i -> new SellTicketThreadLock().start());

        //IntStream.range(0, 5).forEach(i -> new SellTicketThreadReentrantLock().start());

    }
}