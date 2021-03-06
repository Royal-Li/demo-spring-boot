package com.lzscoding.demobase.completablefuture._05_CompletableFuture_expand;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * 线程只是观察的一个维度
 * thenCompose 会把  SmallTool.printTimeAndThread("服务员A 准备打饭，但是被领导叫走，打饭交接给服务员B");
 *             添加到厨师任务末尾 当做一个人物提交到线程池
 * thenComposeAsync 会把服务员A的操作当成单独任务提交到线程池 所以线程名称不同
 *
 */
public class _02_thenCompose {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> {
            SmallTool.printTimeAndThread("服务员A 准备打饭，但是被领导叫走，打饭交接给服务员B");

            return CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("服务员B 打饭");
                SmallTool.sleepMillis(100);
                return dish + " + 米饭";
            });
        });

        SmallTool.printTimeAndThread(cf1.join()+"好了，开饭");
    }
}
