package com.lzscoding.demo.netty.buffer;

import java.nio.ByteBuffer;

/**
 * 存取顺序要一致
 */
public class ByteBufferPutGetTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);


        //类型化放入数据
        buffer.putInt(100);
        buffer.putLong(1000);
        buffer.putShort((short) 1);
        buffer.putChar('李');

        //反转 position 反转
        buffer.flip();


        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());


    }
}
