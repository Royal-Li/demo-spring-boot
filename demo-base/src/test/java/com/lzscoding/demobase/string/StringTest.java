package com.lzscoding.demobase.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * String 类test
 */
@Slf4j
public class StringTest {

    @Test
    public void test1() {
        log.info("验证线程安全 多跑几遍 会发现string buffer 也不一定是999 有可能变小");
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
//        CountDownLatch countDownLatch2 = new CountDownLatch(5000);
        final String[] string = {"im string"};
        StringBuffer stringBuffer = new StringBuffer("im string buffer");
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append("im string builder");
        IntStream.range(1, 1000).forEach(i -> executorService.execute(() -> {
            stringBuffer.append(1);
        }));
        IntStream.range(1, 1000).forEach(i -> executorService.execute(() -> {
            stringBuilder.append(1);
        }));


        log.warn("stringBuffer:{} , stringBuilder:{}", stringBuffer.length() - 16, stringBuilder.length() - 17);
    }

    @Test
    public void test2() {

    }

}


