package com.lzscoding.demobase.test;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloWorld {
    @Test
    public void helloWorldTest() {
        System.out.println("hello world");
    }

    @Test
    public void test1() {

        List<Integer> list = Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99);
        list.forEach(i -> {
            if (i % 2 == 0) {
                return;
            }
            System.out.println(i);
        });

        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).parallel().map(l -> {
            System.out.println(Thread.currentThread().getName() + "--" + l);
            return l;
        }).collect(Collectors.toList()));

    }

}
