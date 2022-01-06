package com.oowanghan.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * NIO服务端
 *
 * @Author WangHan
 * @Create 2019/9/8 10:55 下午
 */
@Slf4j
public class NioServer {

    private static Integer count = 1;
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {

        Map<String, SocketChannel> socketContains = new HashMap<>(64);
        ServerSocketChannel serverSocketChannel = null;

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("192.168.0.105", 1502));
            //配置非阻塞
            serverSocketChannel.configureBlocking(false);
            while (true) {
                log.info("<===========================start===================================>");
                log.info("[wait connect] LocalAddress:{}", serverSocketChannel.getLocalAddress());
                //监听是否有连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    log.info("[no connect]");
                    //遍历套接字容器是否收到消息
                    receiveSocketData(socketContains);

                    log.info("<===========================end===================================>");
                    //等待1s
                    Thread.sleep(5000);
                } else {
                    //设置当前套接字获取数据时为非阻塞
                    socketChannel.configureBlocking(false);
                    //有人连接,向容器中添加连接成功的套接字
                    socketContains.put("第" + count++ + "个socketChannel", socketChannel);
                    //遍历套接字容器是否收到消息
                    receiveSocketData(socketContains);
                    log.info("<===========================start===================================>");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void receiveSocketData(Map<String, SocketChannel> socketContains) {
        log.info("[For SocketContains]");
        //缓冲区
        //表示无人连接
        log.info("[no client connect]");
        //遍历套接字容器是否收到消息
        socketContains.forEach((name, oldSocketChannel) -> {
            try {
                int read = oldSocketChannel.read(buffer);
                if (read != 0) {
                    //写完数据需要开始读的时候，将buffer的position位置放到开头
                    buffer.flip();
                    log.info("[receive data] client:{}, context:{}", name, new String(buffer.array()).trim());
                }else {
                    log.info("[no send data] client:{}", name);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
