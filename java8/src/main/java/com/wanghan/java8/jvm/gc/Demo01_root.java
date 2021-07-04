package com.wanghan.java8.jvm.gc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author WangHan
 * @Create 2020/5/31 5:35 下午
 */
public class Demo01_root {

    /**
     *  jmap -dump:format=b,live,file=1.bin 88158
     *  format=b:以二进制方式存储
     *  live:抓快照的时候只看存活的对象，会主动初始化一次gc
     *  file:存在哪个文件下
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>(1024*1024*100);
        list.add("a");
        list.add("b");
        System.out.println(1);
        System.in.read();

        list = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
