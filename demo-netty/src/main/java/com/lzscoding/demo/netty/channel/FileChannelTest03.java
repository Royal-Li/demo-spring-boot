package com.lzscoding.demo.netty.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用一个buffer 和 fileChannel 的 read write 完成文件读写
 */
public class FileChannelTest03 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/file/channel/file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "/file/channel/file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            //这里有个重要操作，一定不要忘记了
            /**
             *  public final Buffer clear() {
             *         position = 0;
             *         limit = capacity;
             *         mark = -1;
             *         return this;
             *     }
             */
            byteBuffer.clear();

            int read = fileChannel01.read(byteBuffer);
            System.out.println("read = " + read);
            if (read == -1) { //表示读取完毕
                break;
            }
            //将buffer中的数据 写入到 fileChannel02  -- file02.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
        //关闭流
        fileInputStream.close();
        fileOutputStream.close();

    }
}
