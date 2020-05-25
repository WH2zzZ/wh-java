package com.wanghan.java8.jvm;

/**
 * 参数
 *
 * @Author WangHan
 * @Create 2020/4/5 2:12 上午
 */
public class class01_FirstParam {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("Param:" + arg);
        }
        System.out.println(Runtime.getRuntime().maxMemory()/1000/1000 + "M");
    }
}
