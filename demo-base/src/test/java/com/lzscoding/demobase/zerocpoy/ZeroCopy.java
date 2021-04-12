package com.lzscoding.demobase.zerocpoy;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * java零拷贝技术
 * rocketmq commitlog
 */
public class ZeroCopy {

    private static final String PREFIX = "E:\\02private\\01idea\\demo-spring-boot\\demo-base\\src\\main\\resources\\static\\";

    public static void main(String[] args) throws IOException {
        File file = new File(PREFIX + "2K.txt");
        //mmap方式,映射2K,生成的文件也就是2K
        final RandomAccessFile rw = new RandomAccessFile(file, "rw");
        final MappedByteBuffer map = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 2048);
        map.put("mmap content".getBytes());
        rw.close();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            FileOutputStream outputStream = new FileOutputStream(PREFIX + "2K_copy.txt");
            FileChannel outputStreamChannel = outputStream.getChannel();
            fileChannel.transferTo(0, file.length(), outputStreamChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
