package com.lzscoding.demobase.pattern.proxy.dynamic;

public class DynamicProxyTest {
    public static void main(String[] args) {
        //创建目标对象
        TeacherDao target = new TeacherDaoImpl();
        //给目标对象,创建代理对象
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(target).getProxyInstance();
        //通过代理对象调用目标对象方法
        proxyInstance.teach();
        System.out.println("proxyInstance " + proxyInstance + "请注意类名");
        System.out.println("proxyInstance Type" + proxyInstance.getClass() + "有$符号说明是代理对象");
    }
}
