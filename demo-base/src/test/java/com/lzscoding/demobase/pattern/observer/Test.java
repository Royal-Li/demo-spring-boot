package com.lzscoding.demobase.pattern.observer;

/**
 * 随意写
 */


public class Test {

    public static void main(String[] args) {
        WeChatSubject weChatSubject = new WeChatSubject();

        UserObserve user1 = new UserObserve("aaa");
        UserObserve user2 = new UserObserve("bbb");
        new UserObserve("ccc", weChatSubject);
        weChatSubject.registerObserve(user1);
        weChatSubject.registerObserve(user2);
        weChatSubject.setInformation(" 有人向你打了招呼 ");

        weChatSubject.removeObserve(user1);
        weChatSubject.setInformation(" 重大新闻 ");
    }
}
