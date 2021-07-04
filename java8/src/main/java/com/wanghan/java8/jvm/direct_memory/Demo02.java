package com.wanghan.java8.jvm.direct_memory;

import sun.nio.ch.DirectBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存，oom异常
 * @Author WangHan
 * @Create 2020/5/31 4:40 下午
 */
public class Demo02 {

    /**
     * -XX:+DisableExplicitGC 禁用显示的垃圾回收让System.gc()无效，y用于System.gc()触发的是fullGC，会对影响到系统运行
     * 所以禁用后会对直接内存的回收有影响，这样推荐使用Unsafe对象直接使用freeMemory来释放直接内存,可使用如下代码回收
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        System.out.println("分配");
        System.in.read();


        System.out.println("垃圾回收");
        byteBuffer = null;
        System.gc();
        System.in.read();

        if (byteBuffer.isDirect()){
            ((DirectBuffer)byteBuffer).cleaner().clean();
        }
    }
}
