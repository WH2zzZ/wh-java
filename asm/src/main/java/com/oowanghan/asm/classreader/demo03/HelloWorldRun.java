package com.oowanghan.asm.sample.create_class;


import java.lang.reflect.Method;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass("com.oowanghan.asm.classreader.demo01.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);

        Method method = clazz.getDeclaredMethod("test");
        method.invoke(instance);
    }
}