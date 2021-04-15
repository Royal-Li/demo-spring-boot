package com.lzscoding.demobase.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A1B2C3LockCondition {

    static Lock reentrantLock = new ReentrantLock();
    static Condition printCharCondition = reentrantLock.newCondition();
    static Condition printNumCondition = reentrantLock.newCondition();
    static char[] chars = new char[26];
    static int[] nums = new int[26];

    public static void main(String[] args) {

        for (int i = 0; i < 26; i++) {
            chars[i] = (char) (i + 65);
            nums[i] = i;
        }
        new PrintCharThread().start();
        new PrintNumThread().start();


    }

    static class PrintCharThread extends Thread {
        @Override
        public void run() {
            try {
                reentrantLock.lock();
                for (char c : chars) {
                    System.out.print(c);

                    printNumCondition.signal();
//                    sleep(1000);
                    printCharCondition.await();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    static class PrintNumThread extends Thread {
        @Override
        public void run() {
            try {
                reentrantLock.lock();
                for (int n : nums) {
                    System.out.print(n);

                    printCharCondition.signal();
//                    sleep(1000);
                    printNumCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

}
