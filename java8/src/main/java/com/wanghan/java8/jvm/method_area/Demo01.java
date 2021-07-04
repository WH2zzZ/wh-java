package com.wanghan.java8.jvm.method_area;

/**
 * @Author WangHan
 * @Create 2020/5/28 10:56 下午
 */
public class Demo01 {

    public static void main(String[] args) {
        String s1 = "a";
        String x = "a";
        String s2 = "b";
        String s3 = "a" + "b";
        String s4 = s1 + s2;
        String s5 = "ab";
        String s6 = s4.intern();

        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);

        String x2 = new String("c") + new String("d");
        String x3 = x2.intern();
        String x1 = "cd";
//        String x3 = x2.intern();

        System.out.println(x1 == x2);
        System.out.println(x1 == x3);
    }
}
