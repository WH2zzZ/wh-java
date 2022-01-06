package com.oowanghan.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Bio客户端
 *
 * @Author WangHan
 * @Create 2019/9/8 8:47 下午
 */
@Slf4j
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();

        Scanner scanner = new Scanner(System.in);
        socket.connect(new InetSocketAddress("127.0.0.1", 9876));
        while (true){
            log.info("[Connect Success] please input");
            String next = scanner.next();
            socket.getOutputStream().write(next.getBytes());
        }
    }
}
