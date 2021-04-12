package com.lzscoding.demobase.classloader;

public class ClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        // 父子关系AppClassLoader -> ExtClassLoader -> BootStrap ClassLoader
        ClassLoader classLoader1 = ClassLoaderDemo.class.getClassLoader();
        System.out.println("classLoader1 ->" + classLoader1);
        System.out.println("parent of classLoader1 ->" + classLoader1.getParent());
        //BootStrap ClassLoader 由C++开发,是jvm虚拟机的一部分,本身不是java类
        System.out.println("grant parent of classLoader1 ->" + classLoader1.getParent().getParent());

        //String Int 等基础类由Bootstrap ClassLoader加载
        ClassLoader classLoader2 = String.class.getClassLoader();
        System.out.println("classLoader2 -> " + classLoader2);
        System.out.println(classLoader1.loadClass("java.util.List").getClass().getClassLoader());

        //java指令可以通过增加-verbose:class -verbose:gc 参数在启动时打印出类加载情况
        //BootStrap ClassLoader, 加载java基础类,这个属性不能再java指令中指定,腿短不是有java语言处理
        System.out.println("BootStrap ClassLoader加载目录: " + System.getProperty("sun.boot.class.path"));
        //Ext ClassLoader 加载JAVA_HOME/ext下的jar包, 可通过-D java.ext.dirs另行指定目录
        System.out.println("Ext ClassLoader加载目录: " + System.getProperty("java.ext.dirs"));
        //AppClassLoader 加载CLASSPATH, 应用下的jar包,可通过-D java.class.path 另行指定目录
        System.out.println("AppClassLoader加载目录: " + System.getProperty("java.class.path"));
    }
}
