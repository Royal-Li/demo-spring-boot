package com.lzscoding.demo.spring.abstractt;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 180626
 */
public abstract class AbstractParent {

    List<Integer> total = new ArrayList(Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99));

    private void startQueue() {
        int index = 0;
        while (index < total.size()) {
            excute(total.get(index));
            total.remove(index);
        }
    }

    abstract boolean excute(int num);

    abstract String name();

    /**
     * AbstractParent 有两个子类
     * 会启动两个线程去执行
     */
    @PostConstruct
    public void start() {
        new Thread(this::startQueue).start();
    }

}
