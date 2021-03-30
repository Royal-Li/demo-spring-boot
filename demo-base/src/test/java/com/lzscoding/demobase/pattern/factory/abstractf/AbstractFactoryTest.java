package com.lzscoding.demobase.pattern.factory.abstractf;

import com.lzscoding.demobase.pattern.factory.abstractf.car.Car;

/**
 * 抽象工厂模式
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        AbstractFactory abstractFactory = AbstractFactory.getFactory("car");
        abstractFactory.getCar("bmw").createCar();
        AbstractFactory.getFactory("color").getColor("black").addColor();
        try {
            AbstractFactory.getFactory("color").getColor("red").addColor();
        } catch (Exception e) {
            e.printStackTrace();
        }


        AbstractFactory.getFactory("ferrari").getCar("ferrari").createCar();
        AbstractFactory.getFactory("ferrari").getColor("red").addColor();


    }
}
