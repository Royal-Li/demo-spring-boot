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

        System.out.println(Integer.MAX_VALUE + "  -->  2的31次方减1  -->  " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE + "  -->  2的32次方     -->  " + Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
    }

    @Test
    public void test2() {
        Integer integer1 = new Integer(10);
        Integer integer2 = new Integer(10);
        int i1 = 10;
        System.out.println(integer1.equals(integer2));
        System.out.println(integer1 == integer2);
        System.out.println(integer1 == i1);


        Integer integer3 = new Integer(129);
        Integer integer4 = new Integer(129);
        int i2 = 129;
        System.out.println(integer3.equals(integer4));
        System.out.println(integer3 == integer4);
        System.out.println(integer3 == i2);
    }

    @Test
    public void test3() {
        //66转2进制码
        String binaryString = Integer.toBinaryString(66);
        //66的2进制码转十进制
        int i = Integer.parseInt("000000" + binaryString, 2);
        System.out.println(binaryString + " 二进制转十进制 " + i);
        System.out.println(Integer.highestOneBit(66));
    }

}
