package com.lzscoding.demobase.java8.stream;

import com.lzscoding.demobase.domain.User;
import com.lzscoding.demobase.enums.GenderEnum;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest3 {

    public static void test1() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<User> userList = new ArrayList<>();
        userList.add(new User(8L, "hamasa", 88, "88.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(1L, "tom", 111, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(6L, "jason", 66, "66.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(5L, "peter", 55, "55.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(2L, "jack", 22, "22.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(7L, "wali", 77, "77.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(3L, "jerry", 33, "33.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisa", 44, "44.@qq.com", GenderEnum.Female, new Date()));
        //超过66岁的人 名字key 实体 value
        Map<String, User> userOver66 = userList.stream().filter(u -> u.getAge() >= 66).collect(Collectors.toMap(User::getName, u -> u));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + userOver66);
    }

    public static void test2() {
        System.out.println("----------------------------------");
        List<User> userList = new ArrayList<>();
        userList.add(new User(8L, "hamasa", 88, "88.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(1L, "tom", 111, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(6L, "jason", 66, "66.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(5L, "peter", 55, "55.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(2L, "jack", 22, "22.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(7L, "wali", 88, "77.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(3L, "jerry", 33, "33.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisa", 44, "44.@qq.com", GenderEnum.Female, new Date()));
//        userList.add(User.builder().id(9L).name("zhangfei").build());

        //求总数
        Long count = userList.stream().collect(Collectors.counting());
        long count1 = userList.stream().count();
        //求平均年龄
        Double aveAge = userList.stream().collect(Collectors.averagingInt(User::getAge));
        //求最年长
        Optional<User> oldMax1 = userList.stream().collect(Collectors.maxBy(Comparator.comparing(User::getAge)));
        Optional<Integer> oldMax2 = userList.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compareTo));
        //年龄之和
        Integer sumAge = userList.stream().collect(Collectors.summingInt(User::getAge));
        //统计所有信息
        DoubleSummaryStatistics collect = userList.stream().collect(Collectors.summarizingDouble(User::getAge));

        System.out.println("人数: " + count + "---" + count1);
        System.out.println("平均年龄: " + aveAge);
        System.out.println("年龄最大: " + oldMax1 + "----" + oldMax2);
        System.out.println("年龄之和: " + sumAge);
        System.out.println("人员年龄所有统计:" + collect);

    }

    public static void test3() {
        System.out.println("----------------------------------");
        List<User> userList = new ArrayList<>();
        userList.add(new User(8L, "hamasa", 88, "22.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(1L, "tom", 111, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(6L, "jason", 66, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(5L, "peter", 55, "11.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(2L, "jack", 22, "22.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(7L, "wali", 88, "11.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(3L, "jerry", 33, "22.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisa", 44, "22.@qq.com", GenderEnum.Female, new Date()));

        //按照55年龄分组
        Map<Boolean, List<User>> ageGroup = userList.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 55));
        //按照性别分组
        Map<GenderEnum, List<User>> genderGroup = userList.stream().collect(Collectors.groupingBy(User::getGender));
        //先按性别再按年龄
        Map<GenderEnum, Map<Boolean, List<User>>> ageGenderGroup = userList.stream().collect(Collectors.groupingBy(User::getGender, Collectors.partitioningBy(x -> x.getAge() > 55)));
        //名字拼接
        String join = userList.stream().map(User::getName).collect(Collectors.joining(","));
        //按照年龄排序
        List<Integer> ageSort = userList.stream().map(User::getAge).sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        List<String> ageSortName = userList.stream().sorted(Comparator.comparing(User::getAge)).map(User::getName).collect(Collectors.toList());

        System.out.println("按年龄分组: " + ageGroup);
        System.out.println("按性别分组: " + genderGroup);
        System.out.println("按照年龄和性别分组: " + ageGenderGroup);
        System.out.println("姓名拼接: " + join);
        System.out.println("年龄排序: " + ageSort);
        System.out.println("年龄排序: " + ageSortName);


    }

    public static void test4() {
        System.out.println("--------------------------------");


    }

    public static void test5() {
        System.out.println("--------------------------------");
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);

    }


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
