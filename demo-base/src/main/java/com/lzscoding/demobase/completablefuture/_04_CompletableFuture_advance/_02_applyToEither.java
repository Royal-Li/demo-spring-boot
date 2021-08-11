package com.lzscoding.demobase.completablefuture._04_CompletableFuture_advance;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * 上个和这个任务一起执行哪个先执行完成 就要哪个的结果
 *
 * 另一个场景 打电话 1分钟内如果接通就与对方童话 如果不通就挂断
 */
public class _02_applyToEither {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("张三走出餐厅，来到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到来");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在赶来");
            SmallTool.sleepMillis(100);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800路公交正在赶来");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> firstComeBus);

        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
    }
}
