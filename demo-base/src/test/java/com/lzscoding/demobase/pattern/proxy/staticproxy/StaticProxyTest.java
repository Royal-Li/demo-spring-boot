package com.lzscoding.demobase.pattern.proxy.staticproxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        //创建目标对象(被代理对象)
        TeacherDao target = new TeacherDaoImpl();

        //创建代理对象,同事将被代理对象传递给代理对象
        TeacherDao proxy = new TeacherDaoProxy(target);

        //通过代理对象,调用被代理对象方法
        proxy.teach();
    }
}
