package com.lzscoding.demobase.io.bytestream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * （带缓冲区字节输入流） 带缓冲区的处理流，缓冲区的作用的主要目
 * 的是：避免每次和硬盘打交道，提高数据访问的效率。
 */
public class TestBufferedOutputStream {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\file\\IO\\file.png");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "\\file\\IO\\file-copy-2.png");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        //根据文件大小做一个字节
        byte[] bytes = new byte[fileInputStream.available()];
        //将文件上的所有字节读取到数组中
        bufferedInputStream.read(bytes);
        bufferedOutputStream.write(bytes);
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
