package com.lzscoding.demobase.java8.stream;

import com.lzscoding.demobase.domain.User;
import com.lzscoding.demobase.enums.GenderEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest4 {

    @Test
    public void test() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(8L, "hamasa", 88, "88.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(1L, "tom", 111, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(6L, "jason", 66, "66.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(5L, "peter", 55, "55.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(2L, "jack", 22, "22.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(7L, "wali", 77, "77.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(3L, "jerry", 33, "33.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisa", 44, "44.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisa2", 144, "44.@qq.com", GenderEnum.Female, new Date()));
        //将list 转为 map 用 userId作为key User作为value 遇到key相同(即userId相同) 取第一个 并把两个user age相加 赋值给第一个
        Map<Long, User> collect = userList.stream().collect(Collectors.toMap(User::getId, Function.identity(), (x, y) -> {
            x.setAge(x.getAge() + y.getAge());
            return x;
        }));
        System.out.println(collect.size());
        collect.forEach((id, user) -> {
            System.out.println(id + ":" + user);
        });


    }
}
