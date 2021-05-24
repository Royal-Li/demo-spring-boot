package com.lzscoding.demo.netty.client;

import java.io.IOException;
import java.net.Socket;

/**
 * @author 180626
 */
public class SockClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        //向服务器发送数据
        socket.getOutputStream().write("im client jason".getBytes());
        socket.getOutputStream().flush();
        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println(new String(bytes));
        }

    }
}
