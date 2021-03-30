package com.lzscoding.demobase.pattern.factory.abstractf;

import com.lzscoding.demobase.pattern.factory.abstractf.car.Car;
import com.lzscoding.demobase.pattern.factory.abstractf.car.CarFactory;
import com.lzscoding.demobase.pattern.factory.abstractf.car.FerrariCarFactory;
import com.lzscoding.demobase.pattern.factory.abstractf.color.Color;
import com.lzscoding.demobase.pattern.factory.abstractf.color.ColorFactory;

public abstract class AbstractFactory {
    protected abstract Color getColor(String color);

    protected abstract Car getCar(String car);

    public static AbstractFactory getFactory(String choice) {
        if (choice.equals("car")) {
            return new CarFactory();
        } else if (choice.equals("color")) {
            return new ColorFactory();
        } else if (choice.equals("ferrari")) {
            return new FerrariCarFactory();
        }
        return null;
    }
}
