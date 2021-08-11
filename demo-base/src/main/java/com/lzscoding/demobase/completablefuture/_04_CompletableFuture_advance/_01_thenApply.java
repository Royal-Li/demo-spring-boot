package com.lzscoding.demobase.completablefuture._04_CompletableFuture_advance;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * thenApply 把前面执行结果交给后面 看成一个任务 把后面的代码和前面的代码合并 一个线程工作 用来做任务的后置处理
 * thenApplyAsync 和 thenCompose比较像 看成独立的两个任务 两个线程 （也有可能显示一个线程在工作，1 多跑几次 2 或者更改线程池参数就好）
 */
public class _01_thenApply {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白吃好了");
        SmallTool.printTimeAndThread("小白 结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员收款 500元");
            SmallTool.sleepMillis(100);
            return "500";
        }).thenApplyAsync(money -> {
            SmallTool.printTimeAndThread(String.format("服务员开发票 面额 %s元", money));
            SmallTool.sleepMillis(200);
            return String.format("%s元发票", money);
        });

        SmallTool.printTimeAndThread("小白 接到朋友的电话，想一起打游戏");

        SmallTool.printTimeAndThread(String.format("小白拿到%s，准备回家", invoice.join()));
    }


    private static void one() {
        SmallTool.printTimeAndThread("小白吃好了");
        SmallTool.printTimeAndThread("小白 结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员收款 500元");
            SmallTool.sleepMillis(100);
            SmallTool.printTimeAndThread("服务员开发票 面额 500元");
            SmallTool.sleepMillis(200);
            return "500元发票";
        });

        SmallTool.printTimeAndThread("小白 接到朋友的电话，想一起打游戏");

        SmallTool.printTimeAndThread(String.format("小白拿到%s，准备回家", invoice.join()));
    }


    private static void two() {
        SmallTool.printTimeAndThread("小白吃好了");
        SmallTool.printTimeAndThread("小白 结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员收款 500元");
            SmallTool.sleepMillis(100);

            CompletableFuture<String> waiter2 = CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("服务员开发票 面额 500元");
                SmallTool.sleepMillis(200);
                return "500元发票";
            });

            return waiter2.join();
        });

        SmallTool.printTimeAndThread("小白 接到朋友的电话，想一起打游戏");

        SmallTool.printTimeAndThread(String.format("小白拿到%s，准备回家", invoice.join()));
    }
}
