package com.lzscoding.demobase.algorithm;

/**
 * 控制线程交替输出 A1B2C3
 * 使用wait()，notify()和notifyAll()
 * wait()，notify()和notifyAll()这些方法只能在同步中使用，并且这些方法要由锁对象调用(这里很重要!!!!)，并且执行这些方法的线程必须拥有锁对象
 * 当调用这些方法的时候，如果不是用的锁对象，虽然能编译通过，但运行的时候，将得到IllegalMonitorStateException异常
 */
public class A1B2C3 {

    public static void main(String[] args) {
        char[] c = new char[26];
        int[] n = new int[26];
        for (int i = 0; i < 26; i++) {
            c[i] = (char) (i + 65);
            n[i] = i;
        }

        System.out.println((int) 'A');


        new PrintCharThread(c).start();
        new PrintNumThread(n).start();


    }
}

/**
 * 输出字符线程
 */
class PrintCharThread extends Thread {

    private char[] chars;

    public PrintCharThread(char[] chars) {
        this.chars = chars;
    }

    @Override
    public void run() {
        try {
            for (char c : chars) {
                synchronized (A1B2C3.class) {
                    //A1B2C3.class.notifyAll();
                    A1B2C3.class.notify();
                    System.out.print(c);
                    A1B2C3.class.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/**
 * 输出数字线程
 */
class PrintNumThread extends Thread {
    private int[] nums;

    public PrintNumThread(int[] nums) {
        this.nums = nums;
    }

    @Override
    public void run() {
        try {
            for (int n : nums) {
                synchronized (A1B2C3.class) {
                    //A1B2C3.class.notifyAll();
                    A1B2C3.class.notify();
                    System.out.print(n);
                    A1B2C3.class.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

