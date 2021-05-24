package com.lzscoding.demobase.io.characterstream;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * BufferedReader 类： 字符输入流使用的类，加缓冲功能，避免频繁读写硬盘
 * 效果和TestFileReader直接读写一样
 */
public class TestBufferedReader {
    public static void main(String[] args) throws Exception {
        //字符流接收使用String数组
        String[] bufstring = new String[1024];
        //字符流、节点流打开文件类
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\file\\IO\\word.txt");
        //字符流、处理流读取文件类
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int num = 0;
        String readLine = null;
        //BufferedReader.readLine()：单行读取，读取为空返回null
        while ((readLine = bufferedReader.readLine()) != null) {
            bufstring[num++] = readLine;
        }
        bufferedReader.close();
        fileReader.close();
        for (int i = 0; i < num; i++) {
            System.out.println(bufstring[i]);
        }

    }
}
