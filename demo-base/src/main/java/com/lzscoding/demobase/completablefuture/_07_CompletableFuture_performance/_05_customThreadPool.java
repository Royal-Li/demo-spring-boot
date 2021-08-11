package com.lzscoding.demobase.completablefuture._07_CompletableFuture_performance;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 建议反复看视频
 * https://space.bilibili.com/51950540?spm_id_from=333.788.b_765f7570696e666f.1
 */
public class _05_customThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        SmallTool.printTimeAndThread("小白和小伙伴们 进餐厅点菜");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] dishes = IntStream.rangeClosed(1, 8)
                .mapToObj(i -> new Dish("菜" + i, 1))
                .map(dish -> CompletableFuture.runAsync(dish::makeUseCPU, threadPool))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(dishes).join();

        threadPool.shutdown();

        SmallTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));

    }
}
