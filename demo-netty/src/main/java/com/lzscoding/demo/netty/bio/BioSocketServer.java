package com.lzscoding.demo.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author 180626
 * 可以直接启动 运行此类
 * 1 telnet localhost 9000
 * 2 ctrl + ] 建立连接
 * 3 send 字符串
 *
 * 缺点：
 * 1、IO代码里read操作是阻塞操作，如果连接不做数据读写操作会导致线程阻塞，浪费资源
 * 2、如果线程很多，会导致服务器线程太多，压力太大，比如C10K问题
 */
public class BioSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 2).forEach(i -> {
            System.out.println(i);
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "等待连接");
                    //阻塞方法
                    Socket clientSocket = serverSocket.accept();
                    System.out.println(Thread.currentThread().getName() + "有客户端连接了");
                    handler(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        });
    }

    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        while (true) {
            //接受客户端数据,阻塞方法,没有数据可读时就阻塞
            int read = clientSocket.getInputStream().read(bytes);
            System.out.println(Thread.currentThread().getName() + "read 完毕 -->" + read);
            if (read == -1) {
                System.out.println(Thread.currentThread().getName() + " 客户端断开连接" + clientSocket.getPort());
                clientSocket.close();
                //跳出while循环
                break;
            } else {
                System.out.println(Thread.currentThread().getName() + "接收到客户端数据: " + new String(bytes, 0, read));
                clientSocket.getOutputStream().write((Thread.currentThread().getName() + "收到: " + new String(bytes, 0, read)).getBytes());
                clientSocket.getOutputStream().flush();

            }
        }
    }
}
