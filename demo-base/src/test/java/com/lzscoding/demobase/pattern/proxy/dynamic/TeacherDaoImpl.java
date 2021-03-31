package com.lzscoding.demobase.pattern.proxy.dynamic;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中");
    }
}
