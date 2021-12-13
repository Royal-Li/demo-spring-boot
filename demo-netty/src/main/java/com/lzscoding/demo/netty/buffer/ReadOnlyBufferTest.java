package com.lzscoding.demo.netty.buffer;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

public class ReadOnlyBufferTest {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        IntStream.range(0, 63).forEach(i -> {
            buffer.put((byte) i);
        });

        buffer.flip();

        //得到一个只读的buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //读取
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte) 64);

    }
}
