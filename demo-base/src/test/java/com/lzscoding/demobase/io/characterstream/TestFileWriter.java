package com.lzscoding.demobase.io.characterstream;

import java.io.File;
import java.io.FileWriter;

/**
 * 写出字符，使用（字符流）这种方法写出文件比较适合。比如：输出内容添加到word.txt文件
 */
public class TestFileWriter {
    public static void main(String[] args) throws Exception {
        File file = new File(System.getProperty("user.dir") + "\\file\\IO\\word.txt");
        //字符流、节点流写出文件类//new FileWriter(file,true)，这个true代表追加，不写就代表覆盖
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("\n\nfileWriter插入的一行");
        fileWriter.close();
    }
}
