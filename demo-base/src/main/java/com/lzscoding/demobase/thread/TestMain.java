package com.lzscoding.demobase.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class TestMain {

    public static void main(String[] args) {
        //threadEx1();
        threadEx2();
    }

    /**
     * 同步
     * 这个可以不用再去实现线程的接口，不过还是要考虑一下队列满了的丢弃情况
     * 可以打断点 看一下自己电脑的核心线程数和队列长度
     * 此电脑8核  计算出来最大线程数32 队列长度 128
     * 能处理的160个任务  超过160的都会被抛弃
     */
    public static void threadEx1() {
        List<ThreadEntity> listEntity = IntStream.range(0, 161).mapToObj(x -> new ThreadEntity(x)).collect(Collectors.toList());
        List<CompletableFuture<Integer>> listCompletableFuture = listEntity.stream().map(x -> {
            try {
                return CompletableFuture.supplyAsync(() -> x.countPrice(),
                        ThreadPoolManager.getInstance().getPool());
            } catch (RejectedExecutionException e) {
                System.out.println("reject -> " + x);
                log.error("", e);
                return null;
            }
        }).collect(Collectors.toList());
//        List<Integer> result = listCompletableFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
        //优化上面的代码 不然任务超过最大处理能力161 会空指针异常
        List<Integer> result = listCompletableFuture.stream().map(x -> {
            return Optional.ofNullable(x).ofNullable(x.join()).orElse(999);
        }).collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 异步
     * CompletableFuture.runAsync(() -> { System.out.println(Thread.currentThread().getName() + " : 我是异步线程");});
     */
    public static void threadEx2() {
        List<ThreadEntity> listEntity = IntStream.range(0, 500).mapToObj(x -> new ThreadEntity(x)).collect(Collectors.toList());
        List<CompletableFuture> listCompletableFuture = listEntity.stream().map(x -> {
            try {
                return CompletableFuture.runAsync(() -> x.countPrice(), ThreadPoolManager.getInstance().getPool());
            } catch (RejectedExecutionException e) {
                System.out.println("reject -> " + x);
                return null;
            }
        }).collect(Collectors.toList());
        listCompletableFuture.stream().map(CompletableFuture::join);
        System.out.println("异步处理中");
    }
}
