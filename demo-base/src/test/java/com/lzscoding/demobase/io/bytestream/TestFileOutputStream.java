package com.lzscoding.demobase.io.bytestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 字节流：以字节为单位，每次次读入或读出是8位数据。可以读任何类型数据，图片、文件、音乐
 * 视频等。 (Java代码接收数据只能为 byte数组 )
 */
public class TestFileOutputStream {
    public static void main(String[] args) throws Exception {
        //创建字节输入流、节点流方式读取文件
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\file\\IO\\file.png");
        //创建字节输入流、节点流方式输出
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "\\file\\IO\\file-copy.png");
        System.out.println("文件的大小是 : " + fileInputStream.available() + " 个字节");
        //根据文件大小做一个字节
        byte[] bytes = new byte[fileInputStream.available()];
        //将文件上的所有字节读取到数组中
        fileInputStream.read(bytes);
        //将数组中的所有字节一次写到了文件上
        fileOutputStream.write(bytes);
        fileInputStream.close();
        fileOutputStream.close();
    }
}
