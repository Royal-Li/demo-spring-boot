package com.lzscoding.demobase.io.characterstream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * BufferedWriter ： 增加缓冲功能，避免频繁读写硬盘。 我这里： //new FileWriter(file)，这里我只给了他文件位置我没加true代表覆盖源
 */
public class TestBufferedWriter {
    public static void main(String[] args) throws Exception {
        File file = new File(System.getProperty("user.dir") + "\\file\\IO\\word.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("\n    BufferedWriter增加的行");
        bufferedWriter.close();
        fileWriter.close();
    }
}
