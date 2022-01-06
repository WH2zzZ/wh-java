package com.oowanghan.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @Author WangHan
 * @Create 2019/9/15 10:41 下午
 */
public class UDP_NonBlockingNIOSend {

    public static void main(String[] args) throws IOException {

        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("NET-IO".getBytes());
        buffer.flip();
        datagramChannel.send(buffer, new InetSocketAddress("255.255.255.255", 48899));
        buffer.clear();

        datagramChannel.close();
    }
}
