package com.lzscoding.demobase.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {

    /**
     * 定义存储观察者列表
     */
    List<Observe> observeList = new ArrayList<>();

    /**
     * 注册观察者
     *
     * @param observe
     */
    public void registerObserve(Observe observe) {
        observeList.add(observe);
    }

    /**
     * 注销观察者
     *
     * @param observe
     */
    public void removeObserve(Observe observe) {
        observeList.remove(observe);
    }

    /**
     * 推送消息给观察者
     * @param information
     */
    public void notifyAllObServe(String information) {
        for (Observe observe : observeList) {
            observe.update(information);
        }
    }

    public abstract void setInformation(String information);

}
