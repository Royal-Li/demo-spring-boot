package com.lzscoding.demobase.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

/**
 * 用于多线程测试实体类
 */
@Data
@AllArgsConstructor
public class ThreadEntity {
    private int num;

    public int countPrice() {
        int a = RandomUtils.nextInt(1, 10);
        try {
            Thread.sleep(a * 1000);
            System.out.println(String.format("%s:%s  %s:%s", "传入参数", num, "休眠秒数", a));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num;
    }
}
