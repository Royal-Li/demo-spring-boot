package com.lzscoding.demo.netty.buffer;

import java.nio.IntBuffer;
import java.util.stream.IntStream;

/**
 * 缓冲区buffer
 * 缓冲区本质上是一个可以读写数据的内存块
 */
public class IntBufferTest {
    public static void main(String[] args) {
        //规定buffer大小
        IntBuffer intBuffer = IntBuffer.allocate(5);
        IntStream.range(10, 15).forEach(i -> {
            intBuffer.put(i);
            System.out.println(String.format("buffer capacity %s , limit %s , position %s ", intBuffer.capacity(), intBuffer.limit(), intBuffer.position()));
        });
        //如何从buffer读取数据 将buffer反转 读写切换  position反转
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(String.format("buffer capacity %s , limit %s , position %s , 输出 %s ", intBuffer.capacity(), intBuffer.limit(), intBuffer.position(), intBuffer.get()));
        }
    }
}
