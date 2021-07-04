package com.wanghan.java8.jvm.classload;

import java.util.UUID;

/**
 * @Author WangHan
 * @Create 2020/4/5 12:55 下午
 */
public class Son extends Parent{

    public static String RANDOM = UUID.randomUUID().toString();

    static {
        System.out.println("Son init");
    }
}
