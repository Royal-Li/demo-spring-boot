package com.lzscoding.demobase.thread.util;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * SemaphoreDemo
 * Semaphore是计数信号量。Semaphore管理一系列许可。
 * 每个acquire方法阻塞，直到有一个许可证可以获得然后拿走一个许可证；
 * 每个release方法增加一个许可，这可能会释放一个阻塞的acquire方法。
 * 然而，其实并没有实际的许可这个对象，Semaphore只是维持了一个可获得许可证的数量。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //创建三个信号量
        Semaphore semaphore = new Semaphore(3);

        IntStream.range(0, 10).forEach(i -> {
            new SemaphoreThread(semaphore).start();
        });
    }

}


class SemaphoreThread extends Thread {

    Semaphore semaphore;

    public SemaphoreThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            System.out.println(getName() + "进入营业厅, 当前窗口空闲数量" + semaphore.availablePermits());
            sleep(1000);
            semaphore.acquire();
            System.out.println(getName() + "拿到许可证,去办理业务, 当前窗口空闲数量" + semaphore.availablePermits() + "排队等待长度" + semaphore.getQueueLength());
            //模拟随机办理业务时间
            sleep(new Random().nextInt(3000));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("业务办理完成 释放窗口,当前窗口空闲数量" + semaphore.availablePermits());
            semaphore.release();
        }
    }
}
