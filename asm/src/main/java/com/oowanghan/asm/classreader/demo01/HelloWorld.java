package com.oowanghan.asm.classreader.demo01;

/**
 * 对于test()方法，在“方法进入”时和“方法退出”时，添加一条打印语句。
 *
 * 在“方法进入”时，预期目标如下所示：
 *      System.out.println("Method Enter...");
 *
 * 在“方法退出”时，预期目标如下所示：
 *      System.out.println("Method Exit...");
 */
public class HelloWorld {
    public void test() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("this is a test method.");
    }
}