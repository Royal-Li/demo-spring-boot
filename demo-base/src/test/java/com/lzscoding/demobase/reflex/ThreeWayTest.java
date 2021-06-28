package com.lzscoding.demobase.reflex;

import com.lzscoding.demobase.domain.User;

/**
 * 反射的三种方式
 * 在JDK中，主要由以下类来实现 Java 反射机制，除了 Class 类，一般位于 java.lang.reflect 包中
 *
 * java.lang.Class ：一个类
 * java.lang.reflect.Field ：类的成员变量(属性)
 * java.lang.reflect.Method ：类的成员方法
 * java.lang.reflect.Constructor ：类的构造方法
 * java.lang.reflect.Array ：提供了静态方法动态创建数组，访问数组的元素
 * ————————————————
 * 版权声明：本文为CSDN博主「ConstXiong」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/meism5/article/details/103117185
 */
public class ThreeWayTest {
    public static void main(String[] args) throws ClassNotFoundException {
        User user = new User();
        Class classObj1 = user.getClass();
        //这里的getName()
        System.out.println(classObj1.getName());

        Class<?> classObj2 = Class.forName("com.lzscoding.demobase.domain.User");
        System.out.println(classObj2.getName());

        Class<User> classObj3 = User.class;
        System.out.println(classObj3.getName());

    }
}
