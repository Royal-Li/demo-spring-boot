package com.lzscoding.demobase.java8.stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 多跑几遍用例 观察结果
 * .parallel 并行处理的线程名称
 *  forkJoin框架？
 */
public class StreamParallel {
    public static void main(String[] args) {
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).parallel().map(l -> {
            System.out.println(String.format("线程 %s 输出结果 : %s", Thread.currentThread().getName(), l));
            return l;
        }).collect(Collectors.toList()));
    }
}
