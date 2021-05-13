package com.lzscoding.demobase.test;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    }

}
