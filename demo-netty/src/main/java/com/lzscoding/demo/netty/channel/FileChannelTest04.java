package com.lzscoding.demo.netty.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 使用channel transferFro
 * 方法拷贝文件
 */
public class FileChannelTest04 {
    public static void main(String[] args) throws Exception {
        //创建相关的流
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/file/Pic/git.png");
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "/file/channel/git-transfer.png");

        //获取各个流对应的 fileChannel
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        fileOutputStreamChannel.transferFrom(fileInputStreamChannel, 0, fileInputStreamChannel.size());


        //关闭通道和流
        fileInputStream.close();
        fileOutputStream.close();


    }
}
