package com.wanghan.java8.jvm.gc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author WangHan
 * @Create 2020/5/31 5:35 下午
 */
public class Demo02_SetXms_Xmx_NewRatio {

    /**
     *  -Xms600m : 初始堆大小，默认物理内存1/64
     *  -Xmx600m : 设置应用程序(不是JVM)能够使用的最大内存数
     *  -XX:NewRatio=4
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
