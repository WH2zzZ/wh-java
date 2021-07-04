package com.wanghan.java8.jvm.classload;

/**
 * 自定义类加载
 * @Author WangHan
 * @Create 2021/4/6 1:52 下午
 */
public class CustomerClassLoader {


    /**
     * 类加载器方法介绍
     * getParent() 返回该类加载器的父类加载器
     * loadClass(String name) 加载名称为name的类， 返回结果为java.lang.Class类的实例
     * findClass(String name) 查找名称为name的类， 返回结果为java.lang.Class类的实例
     * findLoadedClass(String name) 查找名称为name的已经被加载的类，返回结果为java.lang.Class类的实例
     * defineClass(String name, byte[] b, int off, int len) 把字节数组b中的内容转换为一个Java类，返回结果为java.lang.Class类的实例
     * resolveClass(Class<?> c) 连接指定的Java类
     * @param args
     */

    /**
     * 获取ClassLoader途径
     * 1. 获取当前类的ClassLoader
     *      clazz.getClassLoader()
     * 2. 获取当前线程上下文的ClassLoader
     *      Thread.currentThread().getContextClassLoader()
     * 3. 获取系统的ClassLoader
     *      ClassLoader.getSystemClassLoader()
     * 4. 获取调用者的ClassLoader
     *      DriverManager.getCallerClassLoader()
     * @param args
     */
    public static void main(String[] args) {

    }

}
