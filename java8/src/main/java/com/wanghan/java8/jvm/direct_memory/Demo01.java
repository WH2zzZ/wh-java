package com.wanghan.java8.jvm.direct_memory;

import javafx.beans.binding.When;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存，oom异常
 * @Author WangHan
 * @Create 2020/5/31 4:40 下午
 */
public class Demo01 {

    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try {
            while (true){
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 10);
                list.add(byteBuffer);
                i++;
            }
        }finally {
            System.out.println(i);
        }
    }
}
