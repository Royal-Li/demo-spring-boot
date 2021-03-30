package com.lzscoding.demobase.pattern.factory.abstractf.car;

import com.lzscoding.demobase.pattern.factory.abstractf.AbstractFactory;
import com.lzscoding.demobase.pattern.factory.abstractf.color.Color;
import com.lzscoding.demobase.pattern.factory.abstractf.color.SpecialRedColor;

/**
 * 法拉利专用车间
 */
public class FerrariCarFactory extends AbstractFactory {
    @Override
    protected Color getColor(String color) {
        if (color.equals("red"))
            return new SpecialRedColor();
        return null;
    }

    @Override
    protected Car getCar(String car) {
        if (car.equals("ferrari"))
            return new FerrariCar();
        return null;
    }
}
