package com.oowanghan.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程实现bio的服务端
 *
 * @Author WangHan
 * @Create 2019/9/8 9:24 下午
 */
@Slf4j
public class BioExecuteServer {
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
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(new ExecuteSocket(socket));
            thread.start();
        }
    }

    static class ExecuteSocket implements Runnable{
        private byte[] bs = new byte[1024];
        private Socket socket;

        public ExecuteSocket(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                log.info("[connect success] wait data.ThreadName:{}, ip:{}, port:{}",
                        Thread.currentThread().getName(), socket.getInetAddress(), socket.getLocalPort());

                socket.getInputStream().read(bs);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("[receive data] context:{}", new String(bs).trim());
        }
    }
}
