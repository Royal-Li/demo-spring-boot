package com.lzscoding.demo.spring.abstractt;

import org.springframework.stereotype.Component;

/**
 * @author 180626
 */
@Component
public class AbstractChildTwo extends AbstractParent {
    @Override
    boolean excute(int num) {
        System.out.println(Thread.currentThread().getName() + " 我是 " + this.getClass() + " 消费到num " + num + " 返回结果 ->> " + false);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    String name() {
        return this.getClass().getName();
    }
}
