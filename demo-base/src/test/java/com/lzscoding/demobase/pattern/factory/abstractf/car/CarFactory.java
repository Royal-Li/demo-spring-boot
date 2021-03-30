package com.lzscoding.demobase.pattern.factory.abstractf.car;

import com.lzscoding.demobase.pattern.factory.abstractf.AbstractFactory;
import com.lzscoding.demobase.pattern.factory.abstractf.color.Color;

/**
 * 通用车场 生产 宝马 奔驰 法拉利
 */
public class CarFactory extends AbstractFactory {
    @Override
    protected Color getColor(String colorType) {
        return null;
    }

    @Override
    protected Car getCar(String carType) {
        Car car = null;
        switch (carType) {
            case "bmw":
                car = new BMWCar();
                break;
            case "benz":
                car = new BenZCar();
                break;
            case "ferrari":
                car = new FerrariCar();
                break;
            default:
                throw new RuntimeException("没有" + carType);
        }
        return car;
    }
}
