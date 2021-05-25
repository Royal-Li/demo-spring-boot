package com.lzscoding.demobase.pattern.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 单例模式
 */
class SingleObject {

    //饿汉式 线程安全 类加载时就初始化,浪费内存,容易产生垃圾对象
//    private static SingleObject instance = new SingleObject();
    private static volatile SingleObject instance;

    //构造函数私有,不被实例化
    private SingleObject() {
    }

    //懒汉式 要保证线程安全需要加锁 synchronized加在方法上效率不高
    public static SingleObject getInstance() {

        if (instance == null) {
            synchronized (SingleObject.class) {
                //两次判断是防止 线程一和线程二同时通过第一层null判断
                // 线程一获取锁 线程二等待锁 线程一创建对象然后释放锁 线程二获取锁又创建一遍对象
                if (instance == null) {
                    //不加volatile关键字可能存在指令重排 132 导致第一次判断instance不为null时，instance引用的对象可能还没有完成初始化
                    //new SingleObject() 1分配对象内存空间 2初始化对象 3设置instance指向内存空间
                    instance = new SingleObject();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("hello single demo");
    }

}


public class SingleTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.range(0, 20).forEach(i -> executorService.execute(() -> {
            System.out.println(SingleObject.getInstance());
        }));
        executorService.shutdown();
    }
}
