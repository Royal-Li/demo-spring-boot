//package com.lzscoding.demobase.base;
//
//import com.person.test.trysomething.mybatisplus.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Box<T> {
//    private T object;
//
//    public void set(T object) { this.object = object; }
//    public T get() { return object; }
//
//    public static void main(String[] args) {
//        Box box2 = new Box();
//        box2.set(User.builder().name("aaaa").build());
//        User user = (User) box2.get();
//        System.out.println(user);
//
//        List<String> list = new ArrayList<>();
//        Box<User> box = new Box<>();
//        box.set(User.builder().name("bbbb").build());
//        System.out.println(box.get());
//    }
//
//
//}
