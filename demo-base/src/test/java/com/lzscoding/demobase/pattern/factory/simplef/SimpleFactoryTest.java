package com.lzscoding.demobase.pattern.factory.simplef;

/**
 * 简单工厂模式
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Car bmw = carFactory.createCar("bmw");
        bmw.create();

    }
}

interface Car {
    void create();
}

class BMWCar implements Car {

    @Override
    public void create() {
        System.out.println("生产一辆宝马");
    }
}

class BenZCar implements Car {

    @Override
    public void create() {
        System.out.println("生产一辆奔驰");
    }
}

class CarFactory {
    public Car createCar(String carType) {
        Car car = null;
        switch (carType) {
            case "bmw":
                car = new BMWCar();
                break;
            case "benz":
                car = new BenZCar();
                break;
            default:
                throw new RuntimeException("没有" + carType);
        }
        return car;
    }
}