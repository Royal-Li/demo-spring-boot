package com.lzscoding.demobase.test;

/**
 * 类初始化顺序 测试
 */
public class InitOrderTest {
    public static void main(String[] args) {
        new Child();
    }
}


class Parent {
    public static String staticNumber = "父类-静态变量";
    public String number = "父类-普通变量";

    static {
        System.out.println(staticNumber);
        System.out.println("父类-静态代码块");
    }

    {
        System.out.println("父类-普通代码块");
    }

    public Parent() {
        System.out.println("父类-构造方法");
    }
}

class Child extends Parent {
    public static String staticNumber = "子类-静态变量";
    public String number = "子类-普通变量";

    static {
        System.out.println(staticNumber);
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类-普通代码块");
    }

    public Child() {
        System.out.println("子类-构造方法");
    }
}
