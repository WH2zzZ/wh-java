package com.oowanghan.asm.sample.create_class;


public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass("com.oowanghan.asm.classreader.demo06.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);

        MyClassLoader2 classLoader2 = new MyClassLoader2();
        Class<?> clazz2 = classLoader2.loadClass("com.oowanghan.asm.classreader.demo06.HelloWorld");
        Object instance2 = clazz2.newInstance();
        System.out.println(instance2);

//        Method method = clazz.getDeclaredMethod("test");
//        method.invoke(instance);
    }
}