package com.lzscoding.demobase.domain;


import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        User user = null;

        Optional.ofNullable(user).ifPresent(x -> System.out.println(x));
        System.out.println("--------------------------------");
        user = User.builder().name("lzs").build();
        Optional.ofNullable(user).ifPresent(x -> System.out.println(x));
    }
}
