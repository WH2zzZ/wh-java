package com.wanghan.java8.jvm.classload;

/**
 * @Author WangHan
 * @Create 2020/4/5 12:55 下午
 */
public class Parent {
    static {
        System.out.println("Parent init");
    }
    public static int a = 1;
    public final static int b = 2;

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        Parent.a = a;
    }

    public static int sum(int a, int b) {
        return a + b;
    }
}
