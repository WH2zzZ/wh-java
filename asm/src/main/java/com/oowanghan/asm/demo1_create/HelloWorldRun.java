package com.oowanghan.asm.demo1_create;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass("com.oowanghan.asm.demo1_create.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }
}