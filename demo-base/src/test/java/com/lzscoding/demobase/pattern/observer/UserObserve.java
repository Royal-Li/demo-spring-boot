package com.lzscoding.demobase.pattern.observer;

import com.lzscoding.demobase.domain.User;

public class UserObserve implements Observe {
    private String name;

    public UserObserve(String name) {
        this.name = name;
    }

    public UserObserve(String name, AbstractSubject sub) {
        this.name = name;
        sub.registerObserve(this);
    }

    @Override
    public void update(String information) {
        System.out.println(name + " : 接收到了微信推送 : " + information + "信息");
    }
}
