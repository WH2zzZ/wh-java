package com.oowanghan.asm.asmprint.model;

/**
 * @Author WangHan
 * @Create 2021/7/5 10:40 下午
 */
public class HelloWorld {

    public static long timer = 11111L;

    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        timer = 0 - l;
        System.out.println("Hello world");
        Thread.sleep(1000);
        timer = timer + System.currentTimeMillis();
        System.out.println("耗时：" + timer);
    }
}
