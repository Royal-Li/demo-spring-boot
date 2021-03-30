package com.lzscoding.demobase.pattern.factory.abstractf.car;

public class BMWCar implements Car {
    @Override
    public void createCar() {
        System.out.println("生产一辆宝马");
    }
}
