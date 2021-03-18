package com.lzscoding.demobase.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ThreadTest {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();
        IntStream.range(0, 100000).forEach(i -> {
            try {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        list.add(random.nextInt());
                    }
                };
                thread.start();
                /*
                 * 演示：线程的加入
                 * 		void join()  等待该线程终止。
                 * 		当前线程执行完毕之前，其它线程不能执行。
                 *      list本身是线程不安全的
                 */
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("时间: " + (end - start));
        System.out.println("大小: " + list.size());
    }
}
