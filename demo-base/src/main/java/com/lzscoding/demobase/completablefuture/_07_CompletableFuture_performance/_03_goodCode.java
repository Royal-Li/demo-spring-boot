package com.lzscoding.demobase.completablefuture._07_CompletableFuture_performance;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * 可以根据自己电脑核心数 调整rang大小
 */
public class _03_goodCode {
    public static void main(String[] args) {
        //-Djava.util.concurrent.ForkJoinPool.common.parallelism=8
        //把commonpool 设置成合适的数值 去优化 具体多少 需要去在线上长期观测
        //注意coomonpool 并不只是为completablefuture服务 而且启动后无法动态修改
        //所以应该重新创建线程池专门给completablefuture使用
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");

        SmallTool.printTimeAndThread("小白和小伙伴们 进餐厅点菜");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] dishes = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> new Dish("菜" + i, 1))
                .map(dish -> CompletableFuture.runAsync(dish::make))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(dishes).join();

        SmallTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));

    }
}
