package com.oowanghan.io.nio.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * 阻塞式NIO
 * 1.通过Channel：负责连接
 *      java.nio.channel接口：
 *      SelectableChannel(FileChannel不可用于阻塞模式)
 *          SocketChannel(TCP)
 *          ServerSocketChannel(TCP)
 *          DatagramChannel(UDP)
 *
 *          Pipe.SinkChannel
 *          Pipe.SourceChannel
 *
 * 2.缓冲区Buffer：负责数据的存取
 * 3.选择器Selector：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 *
 * @Author WangHan
 * @Create 2019/9/15 8:18 下午
 */
public class NonBlockingNIOClient {

    public static void main(String[] args) throws IOException {
        //1。获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9875));

        //切换成非阻塞模式
        socketChannel.configureBlocking(false);


        while (!socketChannel.isConnected()) {
            System.out.println(" because connect need time, so client can do another things");
        }

        //输入数据
        System.out.println("请输入发送消息：");
        Scanner scanner = new Scanner(System.in);
        //2.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (scanner.hasNext()){
            String data = scanner.next();
            buffer.put((data + " >>>>> " + new Date()).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            //4.接受服务端的反馈
//            int len;
//            while ((len = socketChannel.read(buffer)) > 0){
//                buffer.flip();
//                System.out.println(new String(buffer.array(), 0, len));
//                buffer.clear();
//            }
        }


        //4.关闭通道
        socketChannel.close();
    }

}
