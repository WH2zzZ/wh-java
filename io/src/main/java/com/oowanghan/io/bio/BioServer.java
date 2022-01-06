package com.kingsalt.io.server;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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
        while (true){
            log.info("[wait connect] ip:{}, port:{}", serverSocket.getInetAddress(), serverSocket.getLocalPort());
            Socket accept = serverSocket.accept();
            log.info("[connect success] wait data. ip:{}, port:{}", accept.getInetAddress(), serverSocket.getLocalPort());
            accept.getInputStream().read(bs);
            log.info("[connect success] context:{}", new String(bs).trim());
        }
    }
}
