package com.wanghan.java8.jvm.thread_stack;

/**
 * 查看栈帧信息
 * @Author WangHan
 * @Create 2020/5/26 10:52 下午
 */
public class Demo01 {

    private static long count = 0;

    public static void main(String[] args) {
        recursion(0L, 0L, 0L);
    }

    private static void recursion(long l, long l1, long l2) {
        long e = 1, f = 2, g = 3;
        count = count + l + l1 + l2;
    }

    private static void recursion() {
        count++;
    }
}
