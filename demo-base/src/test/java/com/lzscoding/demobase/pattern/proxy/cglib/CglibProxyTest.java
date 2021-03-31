package com.lzscoding.demobase.pattern.proxy.cglib;

public class CglibProxyTest {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDaoImpl();
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(target).getProxyInstance();
        proxyInstance.teach();
    }
}
