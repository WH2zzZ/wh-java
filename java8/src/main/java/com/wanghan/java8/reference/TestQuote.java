package com.wanghan.java8.reference;

public class TestQuote {
    public static void main(String args[]) {
        Demo d1 = new Demo(1);
        Demo d2 = new Demo(2);
        System.out.println(d1.a);
        System.out.println(d2.a);

        System.out.println("-----------调用function01--------------");
        function01(d1, d2);
        System.out.println(d1.a);
        System.out.println(d2.a);

        System.out.println("-----------调用function02--------------");
        function02(d1, d2);
        System.out.println(d1.a);
        System.out.println(d2.a);
    }

    /**
     * 表明java其实还是值传递
     * 只不过这个值是拷贝了一份d1所传递
     *
     * 测试是否为引用传递
     * @Author WangHan
     * @Date 14:53 2019/1/12
     * @Param [d1, d2]
     * @return void
     */
    private static void function01(Demo d1, Demo d2) {
        Demo temp;
        temp = d1;
        d1 = d2;
        d2 = temp;
    }

    private static void function02(Demo d1, Demo d2) {
        int a;
        a = d1.a;
        d1.a = d2.a;
        d2.a = a;
    }
}