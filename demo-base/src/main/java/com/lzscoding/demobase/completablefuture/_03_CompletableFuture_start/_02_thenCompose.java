package com.lzscoding.demobase.completablefuture._03_CompletableFuture_start;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * thenCompose 把前面执行的结果交给后面  前面执行完成 才会执行后面的
 */
public class _02_thenCompose {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(2000);
//            if(true){
//                throw new RuntimeException();
//            }
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMillis(1000);
            return dish + " + 米饭";
        }));

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白开吃", cf1.join()));
    }

    /**
     * 用 applyAsync 也能实现
     */
    private static void applyAsync() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("服务员打饭");
                SmallTool.sleepMillis(100);
                return " + 米饭";
            });
            return "番茄炒蛋" + race.join();
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白开吃", cf1.join()));
    }
}