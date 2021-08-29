package com.oowanghan.classfile.sample;

public class HelloWorld implements Cloneable {
    private static final int intValue = 10;
    private static final Long longValue = 10L;
    private static final HelloWorld helloWorld = new HelloWorld();

    public void test() throws Exception{
        int a = 1;
        int b = 2;
        int c = a + b;
    }

    public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}



