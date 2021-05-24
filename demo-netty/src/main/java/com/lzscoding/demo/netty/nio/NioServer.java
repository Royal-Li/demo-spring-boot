package com.lzscoding.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 总结：如果连接数太多的话，会有大量的无效遍历，假如有10000个连接，其中只有1000个连接有写数据，但是由于其他9000个连接并
 * 没有断开，我们还是要每次轮询遍历一万次，其中有十分之九的遍历都是无效的，这显然不是一个让人很满意的状态
 * @author 180626
 */
public class NioServer {

    /**
     * 保存客户端连接
     */
    private static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //创建nio ServerSocketChannel 与BIO的ServerSocket类似
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 设置ServerSocketChannel 为非阻塞
        serverSocketChannel.configureBlocking(false);
        while (true) {
            //非阻塞模式accept方法不会阻塞,否则会阻塞
            // NIO的非阻塞是由操作系统内部实现的，底层调用了linux内核的accept
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                // 设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                // 保存客户端连接在Lis
                channelList.add(socketChannel);
            }
            Iterator<SocketChannel> channelIterator = channelList.iterator();
            while (channelIterator.hasNext()) {
                SocketChannel sc = channelIterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                // 非阻塞模式read方法不会阻塞，否则会阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据，把数据打印出来
                if (len > 0) {
                    System.out.println(Thread.currentThread().getName() + "接收到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    // 如果客户端断开，把socket从集合中去掉
                    channelIterator.remove();
                    System.out.println(Thread.currentThread().getName() + "客户端断开连接");

                }
            }
        }
    }
}
