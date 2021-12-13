package com.lzscoding.demo.netty.buffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可以让文件在内存（堆外内存）中修改，在操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir") + "/file/channel/mapped_byte_buffer.txt", "rw");

        //获取对应通道
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * READ_WRITE 读写模式
         * 0 直接修改的起始位置
         * 5 是映射到内存的大小,即 将mapped_byte_buffer.txt的多少个字节映射到内存
         * 可以直接修改的范围就是0-5
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'A');
//        mappedByteBuffer.put(8, (byte) '九');

        randomAccessFile.close();
        System.out.println("修改成功");

    }
}
