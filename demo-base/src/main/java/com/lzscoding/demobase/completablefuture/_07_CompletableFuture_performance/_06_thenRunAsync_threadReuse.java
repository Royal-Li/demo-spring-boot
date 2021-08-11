package com.lzscoding.demobase.completablefuture._07_CompletableFuture_performance;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 设置的线程存活时间为0  所以thenRunAsync 是在两个线程中执行的
 * 仅用于自测和研究
 */
public class _06_thenRunAsync_threadReuse {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>());

        CompletableFuture.runAsync(() -> SmallTool.printTimeAndThread("A"), executor)
                .thenRunAsync(() -> SmallTool.printTimeAndThread("B"), executor)
                .join();

    }
}
