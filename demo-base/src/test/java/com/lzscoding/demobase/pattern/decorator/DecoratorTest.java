package com.lzscoding.demobase.pattern.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
//        System.out.println(new Dog().desc());
//        System.out.println(new Cat().desc());
        System.out.println(new WhiteComponentWarpper(new Dog()).desc());
        System.out.println(new DanceComponentWarpper(new WhiteComponentWarpper(new Dog())).desc());
    }
}

/**
 * 创建一个接口
 */
interface Component {
    String desc();
}

/**
 * 接口的抽象装饰类
 */
abstract class ComponentWarpper implements Component {

    private Component componentWarpper;

    public ComponentWarpper(Component component) {
        this.componentWarpper = component;
    }

    @Override
    public String desc() {
        return componentWarpper.desc();
    }
}

/**
 * 被装饰类
 */
class Cat implements Component {

    @Override
    public String desc() {
        return "我是小猫咪";
    }
}

class Dog implements Component {

    @Override
    public String desc() {
        return "我是小狗蛋";
    }
}

/**
 * 装饰类
 */
class WhiteComponentWarpper extends ComponentWarpper implements Component {

    public WhiteComponentWarpper(Component component) {
        super(component);
    }

    @Override
    public String desc() {
        return super.desc() + " | 白色";
    }
}

class DanceComponentWarpper extends ComponentWarpper implements Component {

    public DanceComponentWarpper(Component component) {
        super(component);
    }

    @Override
    public String desc() {
        return super.desc() + " | 跳舞";
    }
}