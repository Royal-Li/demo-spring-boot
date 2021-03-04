package com.lzscoding.demobase.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompareObjAndT {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1aaa");
        list.add("2bbb");
        printCollection1(list);
        //   printCollection2(list);
        test1(list);
        test2(list);
        test3(list);

    }

    private static <T> void printCollection1(Collection<T> c) {
        c.forEach(e -> System.out.println(e));
    }

    private static void printCollection2(Collection<Object> c) {
        c.forEach(e -> System.out.println(e));
    }


    private static <Integer, b> void test1(List<b> c) {
        System.out.println(c);
    }

    private static void test2(List c) {
        System.out.println(c);
    }

    /*
    常用的 T，E，K，V，？
    本质上这些个都是通配符，没啥区别，只不过是编码时的一种约定俗成的东西。
    比如上述代码中的 T ，我们可以换成 A-Z 之间的任何一个 字母都可以，并不会影响程序的正常运行，但是如果换成其他的字母代替 T ，在可读性上可能会弱一些。
    通常情况下，T，E，K，V，？ 是这样约定的：
    ？ 表示不确定的 java 类型
    T (type) 表示具体的一个java类型
    K V (key value) 分别代表java键值中的Key Value
    E (element) 代表Element
     */
    private static <PPPPPPPPPPPPPPPPPP> void test3(List<PPPPPPPPPPPPPPPPPP> c) {
        System.out.println(c);
    }

}
