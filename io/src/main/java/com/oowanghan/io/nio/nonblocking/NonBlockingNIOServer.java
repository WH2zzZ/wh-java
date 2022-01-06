package com.oowanghan.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author WangHan
 * @Create 2019/9/15 9:45 下午
 */
public class NonBlockingNIOServer {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //非阻塞
        serverSocketChannel.configureBlocking(false);

        //2.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9875));

        //3.获取选择器
        Selector selector = Selector.open();

        //4.将通道注册到选择器上，并且监听接受事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //5.轮训式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0){
            //6.获取当前选择器中所有注册的选择键（已就绪的监听事件）
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                //7.判断具体是什么事件准备就绪
                if (selectionKey.isAcceptable()){
                    //8.若"接收就绪"，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //9.切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //10.将该通道注册到选择器上
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //将buffer也传过去， 一般是一个buffer一个client
                    socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                    System.out.println("新的客户端连接 : " + socketChannel.getRemoteAddress());
                    //取消选择键
                    selectionKeys.remove(selectionKey);
                }else if (selectionKey.isReadable()){
                    //11.获取当前选择器上"读就绪"状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();

                    readData(socketChannel, buffer);

//                    System.out.println("请输入返回消息：");
//                    if (scanner.hasNext()){
//                        String response = scanner.next();
                        //此处没有对字节数组做长度判断，可能需要多次传输
//                        buffer.put(response.getBytes());
//                        socketChannel.write(buffer);
//                        buffer.clear();
//                    }
                    //取消选择键
                    selectionKeys.remove(selectionKey);
                }
            }
        }

        serverSocketChannel.close();
    }

    private static void readData(SocketChannel client, ByteBuffer buffer) throws IOException {
        //12.读取数据
        int len;
        if ((len = (client.read(buffer))) > 0) {
            buffer.flip();
            String readData = new String(buffer.array(), 0, len);
            System.out.println("客户端 : " + client.getRemoteAddress() + ", 数据 : " + readData);
            buffer.clear();
        } else if (client.read(buffer) == 0){
            return;
        } else {
            System.out.println("客户端关闭");
            client.close();
            return;
        }
    }
}
