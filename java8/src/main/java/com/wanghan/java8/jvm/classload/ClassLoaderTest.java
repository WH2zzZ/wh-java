package com.wanghan.java8.jvm.classload;


import sun.misc.Launcher;

import java.net.URL;

/**
 * @Author WangHan
 * @Create 2021/4/6 11:29 上午
 */
public class ClassLoaderTest {

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
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);

        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader@452b3a41
        System.out.println(extClassLoader);

        //获取ext上层 引导类加载器 是获取不到的 ，可看下面代码
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        //获取用户自定义类 的类加载器是什么
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        //发现还是AppClassLoader, 所以默认使用的是系统类加载器来加载的
        System.out.println(classLoader);

        //查看String类是谁加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        //发现获取为null
        //获取不到加载器， 说明string是由 引导类加载的
        //也就是说java的核心类库都是引导类加载的
        System.out.println(classLoader1);

        //获取启动类加载器能够加载的api的路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        /*
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/resources.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/rt.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/sunrsasign.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/jsse.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/jce.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/charsets.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/jfr.jar
        file:/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/classes
         */
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }

        String extDirs = System.getProperty("java.ext.dirs");
        /*
        /Users/wanghan/Library/Java/Extensions
        /Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/ext
        /Library/Java/Extensions
        /Network/Library/Java/Extensions
        /System/Library/Java/Extensions
        /usr/lib/java
         */
        for (String s : extDirs.split(":")) {
            System.out.println(s);
        }
    }
}
