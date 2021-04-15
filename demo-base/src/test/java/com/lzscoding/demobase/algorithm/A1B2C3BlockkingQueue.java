package com.lzscoding.demobase.algorithm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列实现A1B2C3
 */
public class A1B2C3BlockkingQueue {

    public static void main(String[] args) {
        char[] chars = new char[26];
        int[] nums = new int[26];
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) (i + 'A');
            nums[i] = i;
        }
        //创建一个阻塞队列容量为2
        BlockingQueue<String> stringBlockingQueue = new ArrayBlockingQueue<>(2);
        new PrintChar(chars,stringBlockingQueue).start();
        new PrintNum(nums,stringBlockingQueue).start();

    }

}

class PrintChar extends Thread {
    private char[] chars;
    private BlockingQueue<String> stringBlockingQueue;

    public PrintChar(char[] chars, BlockingQueue<String> stringBlockingQueue) {
        this.chars = chars;
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        try {
            for (char c : chars) {

                stringBlockingQueue.put("超过两个就阻塞,放入第一个");
                stringBlockingQueue.put("超过两个就阻塞,放入第二个");
                System.out.print(c);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class PrintNum extends Thread {
    private int[] nums;
    private BlockingQueue<String> stringBlockingQueue;

    public PrintNum(int[] nums, BlockingQueue<String> stringBlockingQueue) {
        this.nums = nums;
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        try {
            for (int n : nums) {

                //每次放入两个阻塞,就需要释放两个
                stringBlockingQueue.take();
                stringBlockingQueue.take();
                System.out.print(n);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


