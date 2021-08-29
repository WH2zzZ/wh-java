package com.oowanghan.asm.sample;

public class HelloWorld implements Cloneable {
    private static final int intValue = 10;

    public void test() {
        int a = 1;
        int b = 2;
        int c = a + b;
    }

    public void test(boolean flag) {
        if (flag){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}



