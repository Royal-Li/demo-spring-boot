package com.lzscoding.demobase.pattern.factory.abstractf.color;

import com.lzscoding.demobase.pattern.factory.abstractf.AbstractFactory;
import com.lzscoding.demobase.pattern.factory.abstractf.car.Car;

public class ColorFactory extends AbstractFactory {
    @Override
    protected Color getColor(String colorType) {

        Color color = null;
        switch (colorType) {
            case "white":
                color = new WhiteColor();
                break;
            case "black":
                color = new BlackColor();
                break;
            default:
                throw new RuntimeException("无" + colorType);
        }

        return color;
    }

    @Override
    protected Car getCar(String carType) {
        return null;
    }
}
