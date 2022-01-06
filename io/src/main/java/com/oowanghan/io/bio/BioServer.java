package com.oowanghan.io.bio;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bio服务端
 *
 * @Author WangHan
 * @Create 2019/9/8 7:21 下午
 */
@Data
@Slf4j
public class BioServer {


    public static void main(String[] args) throws IOException {
        byte[] bs = new byte[1024];
        ServerSocket serverSocket = new ServerSocket();
        //设置一个端口号，ip默认为本机
        serverSocket.bind(new InetSocketAddress(9876));
        //accept 专门负责通信，获得socket套接字
        /**
         * 阻塞：释放CPU资源
         * accept阻塞等待连接
         * read阻塞，等待数据，会影响第二个客户端的连接
         * 考虑使用多线程实现
         */
        log.info("[wait connect] ip:{}, port:{}", serverSocket.getInetAddress(), serverSocket.getLocalPort());
        // accept阻塞等待连接
        Socket accept = serverSocket.accept();
        while (true){
            log.info("[connect success] wait data. ip:{}, port:{}", accept.getInetAddress(), serverSocket.getLocalPort());
            // read阻塞，等待数据，会影响第二个客户端的连接
            // 获取的是SocketInputStream
            InputStream acceptInputStream = accept.getInputStream();
            acceptInputStream.read(bs);
            log.info("[connect success] context:{}", new String(bs).trim());
        }
    }
}
