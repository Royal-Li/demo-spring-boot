package com.lzscoding.demo.netty.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest01 {
    public static void main(String[] args) throws Exception {
        String str = "hello lzs";
        //创建一个输出流-》channel
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "/file/channel/file01.txt");
        //通过 fileoutputstream 获取对应的fileChannel
        //这个 fileChannel真是类型是 fileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入到byteBuffer中
        byteBuffer.put(str.getBytes());

        //将byteBuffer 进行flip 反转
        byteBuffer.flip();

        //将 byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();


    }
}
