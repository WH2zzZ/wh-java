package com.oowanghan.asm.classreader.demo03;


import com.oowanghan.asm.sample.create_class.MyClassLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        MyClassLoaderDemo classLoader = new MyClassLoaderDemo();
        Class<?> clazz = classLoader.loadClass("com.oowanghan.asm.classreader.demo03.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);

        Method method = clazz.getDeclaredMethod("add", int.class, int.class);
        System.out.println(method.invoke(instance, 2, 1));

        Method method2 = clazz.getDeclaredMethod("sub", int.class, int.class);
        System.out.println(method2.invoke(instance, 2, 1));

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            String fieldName = f.getName();
            f.setAccessible(true);
            if (fieldName.startsWith("timer")) {
                Object fieldValue = f.get(null);
                System.out.println(fieldName + " = " + fieldValue);
            }
        }
    }
}