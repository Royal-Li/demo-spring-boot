package com.lzscoding.demobase.io.characterstream;

import java.io.FileReader;
import java.io.IOException;

/**
 * 字符流 ：以字符为单位，每次次读入或读出是16位数据。其只能读取字符类型数据。 (Java代码接收数据为一般为 char数组，也可以是别的
 */
public class TestFileReader {
    public static void main(String[] args) throws IOException {
        //字符流接收使用的char数组
        char[] buf = new char[1024];

        //字符流、节点流打开文件类
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\file\\IO\\word.txt");
        //FileReader.read(buf)：取出字符存到buf数组中,如果读取为-1代表为空即结束读取。
        //FileReader.read()：读取的是一个字符，但是java虚拟机会自动将char类型数据转换为int数据，
        //如果你读取的是字符A，java虚拟机会自动将其转换成97，如果你想看到字符可以在返回的字符数前加（char）强制转换如
        while (fileReader.read(buf) != -1) {
        }
        System.out.println("");
        for (char c : buf) {
            System.out.print(c);
        }
    }
}
