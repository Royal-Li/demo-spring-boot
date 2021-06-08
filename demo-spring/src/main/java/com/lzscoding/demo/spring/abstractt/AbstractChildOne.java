package com.lzscoding.demo.spring.abstractt;

import org.springframework.stereotype.Component;

/**
 * @author 180626
 */
@Component
public class AbstractChildOne extends AbstractParent {
    @Override
    boolean excute(int num) {
        System.out.println(Thread.currentThread().getName() + " 我是 " + this.getClass() + " 消费到num " + num + " 返回结果 ->> " + true);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    String name() {
        return this.getClass().getName();
    }
}
