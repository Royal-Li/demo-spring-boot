package com.lzscoding.demobase.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class MyCallable implements Callable<String> {

    Integer param;

    public MyCallable(Integer param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName() + " : 传递的参数是  : " + param;
    }
}

public class ThreadDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 请比较test1 test2 与 test3的不同点   test3执行快 原因如下
         * 调用futureTask.get()方法去获取线程异步的执行结果，
         * 这时候问题又来了
         * 那如果2个线程一起执行，futureTask执行完了，futureTask1没执行完会不会有问题。是不是futureTask1就拿不到结果了？
         * 当然不会，futureTask.get（）方法会保证线程在执行完之前是阻塞的.
         */

        test2();
    }

    public static void test1() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 100; i++) {

            MyCallable myCallable = new MyCallable(i);
            FutureTask futureTask = new FutureTask(myCallable);
//                futureTask.run();
            new Thread(futureTask).start();
            System.out.println("返回结果----->" + futureTask.get());

        }
    }

    public static void test2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            Future<String> callableOutput = executorService.submit(new MyCallable(i));
            System.out.println(callableOutput.get());
        }
        executorService.shutdown();
    }

    public static void test3() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future> futureList = new ArrayList<Future>();
        for (int i = 0; i < 100; i++) {
            Future<String> callableOutput = executorService.submit(new MyCallable(i));
            futureList.add(callableOutput);
        }
        for(Future f : futureList){
            System.out.println(f.get());
        }
        executorService.shutdown();
    }

}
