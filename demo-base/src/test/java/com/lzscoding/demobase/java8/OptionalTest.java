package com.lzscoding.demobase.java8;

import com.lzscoding.demobase.domain.User;
import com.lzscoding.demobase.enums.GenderEnum;

import java.util.Date;
import java.util.Optional;

/**
 * com.lzscoding.demobase.optional 有更详细的示例
 *
 */
public class OptionalTest {

    public static void test1() {
        Optional<String> optional1 = Optional.ofNullable(null);
        System.out.println(optional1.isPresent()+"--"+optional1);

        Optional<User> optionalUser = Optional.ofNullable(new User());
        System.out.println(optionalUser.isPresent());
        System.out.println(optionalUser.orElseGet(() -> new User(1L, "11", 11, "1", GenderEnum.Female, new Date())));
        System.out.println(optionalUser);

        Optional<User> optionalUser2 = Optional.empty();
        System.out.println(optionalUser2.isPresent());
        System.out.println(optionalUser2);
    }

    public static void main(String[] args) {
        test1();
    }
}
