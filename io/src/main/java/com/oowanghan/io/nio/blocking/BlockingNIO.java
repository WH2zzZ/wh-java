package com.oowanghan.io.nio.client;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
public class BlockingNIO {

    /**
     * 客户端
     */
    @Test
    public void testClient() throws IOException {
        //1。获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9876));
        FileChannel inChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);

        //2.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //3.读取本地文件，并发送到服务端
        while (inChannel.read(buffer) != -1){
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //3-2告诉服务端我结束发送了
        socketChannel.shutdownOutput();

        //4.接受服务端的反馈
        int len = 0;
        while ((len = socketChannel.read(buffer)) != -1){
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }

        //4.关闭通道
        inChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.txt"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        //2.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9876));

        //3.获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        //4.分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (socketChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //发送反馈
        byteBuffer.put("服务端成功接受".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        //关闭通道
        socketChannel.close();
        serverSocketChannel.close();
        outChannel.close();
    }
}
