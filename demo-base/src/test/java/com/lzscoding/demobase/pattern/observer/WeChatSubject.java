package com.lzscoding.demobase.pattern.observer;

public class WeChatSubject extends AbstractSubject {

    /**
     * 设置信息并推送给观察者
     *
     * @param information
     */
    @Override
    public void setInformation(String information) {
        System.out.println("收到一条消息 推送中...");
        //向所有观察者服务器推送消息
        notifyAllObServe(information);
    }
}
