package com.wanghan.java8.jvm.classload;

/**
 * @Author WangHan
 * @Create 2020/4/6 10:05 下午
 */
public class Singleton {
    public static int a;

    private static Singleton singleton = new Singleton();

    public static int b = 0;

    private Singleton(){
        a++;
        b++;
//        System.out.println(a);
//        System.out.println(b);
    }


    public static Singleton getInstance(){
        return singleton;
    }

    static {
        System.out.println("Singleton init");
    }
}
