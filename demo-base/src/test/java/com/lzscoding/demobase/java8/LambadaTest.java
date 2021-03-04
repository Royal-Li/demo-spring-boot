package com.lzscoding.demobase.java8;

import com.lzscoding.demobase.domain.User;
import com.lzscoding.demobase.enums.GenderEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LambadaTest {



    public static void test1() {
        Arrays.asList("a", "z", "w", "b", "d").forEach(e -> System.out.println(e));
        Arrays.asList("a", "z", "w", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });

    }

    public static void test2() {
        List<User> userlist = new ArrayList<>();
        User user1 = User.builder().name("大哥").id(1L).age(1).email("1").build();
        User user2 = new User(2L, "二哥", 2, "2", GenderEnum.Male, new Date());
        User user3 = new User(3L, "三哥", 3, "3", GenderEnum.Female, new Date());
        User user4 = User.builder().name("四哥").id(4L).age(4).email("4").build();
        userlist.add(user3);
        userlist.add(user1);
        userlist.add(user2);
        userlist.add(user4);
        userlist.forEach(e -> System.out.println(e));
        System.out.println("\n------------");
        userlist.sort((e1, e2) -> {
            int result = e2.getId().compareTo(e1.getId());
            return result;
        });

        userlist.forEach(e -> {
            System.out.print("\n"+e);
            System.out.print(e.getId());
            System.out.print(e.getName()+"\n");
        });

        System.out.println("\n------------");

    }

    /**
     * lambda表达式 双冒号::用法
     * 这种[方法引用]或者说[双冒号运算]对应的参数类型是Function<T,R>
     * T表示传入类型，R表示返回类型。
     * 比如表达式person -> person.getAge(); 传入参数是person，返回值是person.getAge()，
     * 那么方法引用Person::getAge就对应着Function<Person,Integer>类型。
     */
    public static void test3() {
        //TODO::重点看下此方法上面的注释
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
