package com.lzscoding.demobase.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * “循环栅栏”
 * 所有线程都等待完成后才会继续下一步行动
 * CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
 * CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。
 *
 * CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
 *
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
 *
 * 而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
 *
 * 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
 */
public class CyclicBarrierDemo {
    static final int COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT, () -> {
            System.out.println(Thread.currentThread().getName() + "------全员到齐 开始跑 !--------");
        });

        System.out.println("比赛开始 请就位");
        Thread.sleep(2000);
        IntStream.range(0, 5).forEach(i -> {
            new CyclicBarrierThread(cyclicBarrier).start();
        });

    }

}

class CyclicBarrierThread extends Thread {
    CyclicBarrier cyclicBarrier;

    public CyclicBarrierThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        try {

            System.out.println(getName() + "到达栅栏 A");
            cyclicBarrier.await();

            System.out.println(getName() + "冲破栅栏 A");
            System.out.println(getName() + "到达栅栏 B");
            cyclicBarrier.await();

            System.out.println(getName() + "冲破栅栏 B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}


