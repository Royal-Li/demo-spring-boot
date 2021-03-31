package com.lzscoding.demobase.pattern.proxy.cglib;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中 ,我是cglib代理,不需要实现接口");
    }
}
