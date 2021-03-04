package com.lzscoding.demobase.java8.stream;



import com.lzscoding.demobase.domain.User;
import com.lzscoding.demobase.enums.GenderEnum;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest1 {

    public static void test1() {
        List<Integer> list = Arrays.asList(11, 8, 43, 64, 376, 34, 3, 2, 56, 74, 222, 42, 234, 6, 7567, 6, 45, 6, 547, 58, 9);
        //遍历输出符合条件的元素
        list.stream().filter(x -> x > 100).forEach(System.out::println);
        //查找第一个匹配元素
        Optional<Integer> findFirst = list.stream().filter(x -> x > 100).findFirst();
        //匹配任意 并行流
        Optional<Integer> findAny = list.parallelStream().findAny().filter(x -> x > 100);
        //是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 100);
        System.out.println("匹配第一个值:" + findFirst.get());
        System.out.println("匹配任意一个值:" + findAny.get());
        System.out.println("是否存在大于100的值:" + anyMatch);
        System.out.println("------------------------------------");
    }

    public static void test2() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(8L, "hamasa", 88, "88.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(1L, "tom", 11, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(6L, "jason", 66, "66.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(5L, "peter", 55, "55.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(2L, "jack", 22, "22.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(7L, "wali", 88, "77.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(3L, "jerry", 33, "33.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisa", 44, "44.@qq.com", GenderEnum.Female, new Date()));

        long filterList = userList.stream().filter(x -> x.getGender() == GenderEnum.Female).count();

        Optional<User> oldMax = userList.stream().max(Comparator.comparingInt(User::getAge));

        List<String> j = userList.stream().filter(x -> "j".equals(x.getName().charAt(0) + "")).map(User::getName).collect(Collectors.toList());
        //比较这种拼接和下面的拼接不同 观察
        List<String> upperCaseName = userList.stream().map(User::getName).map(String::toUpperCase).map(x -> x + "--Logo").collect(Collectors.toList());

        List<String> groupNameGender = userList.stream().map(user -> {
            user.setName(user.getName() + "--" + user.getGender().getName());
            return user.getName();
        }).collect(Collectors.toList());

        System.out.println("女性人数:" + filterList);
        System.out.println("最老的人:" + oldMax.get());
        System.out.println("j开头的人名" + j);
        System.out.println("人名大写并加上logo:" + upperCaseName);
        System.out.println("组合姓名和性别" + groupNameGender);
        System.out.println("------------------------------------");

    }

    public static void test3() {
        List<String> list = Arrays.asList("azzzz", "baaa", "efg", "hijklmnop", "qrstuvwxyz", "z");
        Optional<String> max = list.stream().max(String::compareTo);
        Optional<String> max1 = list.stream().max(Comparator.comparing(String::length));
        Optional<String> max2 = list.stream().max(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("ascii码最大和最小--" + max.get() + "--" + "--" + max2.get());
        System.out.println("最长的字符串是--" + max1.get());
        System.out.println("azzzzzzzz".compareTo("ha"));
        System.out.println("------------------------------------");
    }


    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
