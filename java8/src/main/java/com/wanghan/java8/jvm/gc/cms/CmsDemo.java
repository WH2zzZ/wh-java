package com.wanghan.java8.jvm.gc.cms;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WangHan
 * @Create 2022/4/26 7:33 下午
 */
public class CmsDemo {

    private static final int _1M = 1024*1024;

    public static void main(String[] args) throws InterruptedException {

        List<byte[]> byteList = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread.sleep(250);
            applyHeap(byteList, i);
        }
    }

    private static void applyHeap(List<byte[]> byteList, int i) {
        System.out.println(i);
        byte[] test = new byte[_1M];
        byteList.add(test);
    }
}
