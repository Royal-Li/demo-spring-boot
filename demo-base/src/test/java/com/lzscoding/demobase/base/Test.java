package com.lzscoding.demobase.base;

class User{
    public User(String name) {
        this.name = name;
    }

    public String name;

    public String one(){
        return "one" + name;
    }
    public String two(){
        return "two" + name;
    }
}

class UserChild extends User{

    public UserChild(String name) {
        super(name);
    }
    public String one(){
        return "oneChild" + name;
    }
    public String two(){
        return "twoChild" + name;
    }
}

public class Test {

    /**
     * 定义传参为父类
     * @param user
     */
    public static void test1(User user){
        System.out.println(user.one());
    }

    public static void main(String[] args) {
        /**
         * 传参为子类
         */
        test1(new UserChild("lzs"));
    }
}
