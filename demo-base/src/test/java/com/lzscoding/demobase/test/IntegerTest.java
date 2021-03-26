package com.lzscoding.demobase.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class IntegerTest {

    @Test
    public void testBinary() {
        int number = 9;
        log.info("{} 的二进制码为 : {}", number, Integer.toBinaryString(number));
        log.info("{} 的二进制码最高位 : {}", number, Integer.highestOneBit(number));
    }

}
