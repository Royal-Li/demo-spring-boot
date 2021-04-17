package com.lzscoding.demo.spring.ioc;

/**
 * spring 中 ioc 就是工厂加反射
 *
 * @author 180626
 */
public class MyIoc {
    public static void main(String[] args) {
        Fruit instance = FruitFactory.getInstance("com.lzscoding.demo.spring.ioc.Apple");
        if (instance != null) {
            instance.showName();
        }
    }
}

interface Fruit {
    void showName();
}

class Apple implements Fruit {

    @Override
    public void showName() {
        System.out.println("苹果");
    }
}

class Orange implements Fruit {

    @Override
    public void showName() {
        System.out.println("橘子");
    }
}

class FruitFactory {
    public static Fruit getInstance(String className) {
        Fruit f = null;
        try {
            f = (Fruit) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return f;
    }
}