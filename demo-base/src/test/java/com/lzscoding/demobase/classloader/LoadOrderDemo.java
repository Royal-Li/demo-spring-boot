package com.lzscoding.demobase.classloader;

/**
 * 类初始化过程也是就是方法执行过程
 * 父类静态域-子类静态域-父类非静态域-父类构造函数 -子类非静态域-子类构造函数
 */
public class LoadOrderDemo {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        C c = new D();
    }
}


class C {
    static {
        System.out.println("C 基类静态域 ");
    }


    {
        System.out.println("C 基类对象成员构造函数");
    }


    public C() {
        System.out.println("C 基类本身的构造函数");
    }
}

class D extends C {
    static {
        System.out.println("D 派生类静态域");
    }


    {
        System.out.println("D 派生类对象成员构造函数");
    }


    public D() {
        System.out.println("D 派生类本身的构造函数");
    }
}