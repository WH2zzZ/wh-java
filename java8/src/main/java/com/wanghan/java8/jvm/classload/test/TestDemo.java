package com.wanghan.java8.jvm.classload.test;

/**
 * TODO
 *
 * @className: TestDemo
 * @author: wanghan
 * @date: 2022-01-25 22:29
 **/
public class TestDemo {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        MyClassloader1 myClassloader1 = new MyClassloader1();
        Class<?> bClass = Class.forName("com.wanghan.java8.jvm.classload.test.TestB", true, myClassloader1);
        System.out.println("b : " + bClass.getClassLoader());
        System.out.println(TestB.class.getClassLoader());
        TestA.setInvoker((TestB) bClass.newInstance());


        MyClassloader2 classloader2 = new MyClassloader2();
        Class<?> cClass = Class.forName("com.wanghan.java8.jvm.classload.test.TestC", true, classloader2);
        TestC testC = (TestC) cClass.newInstance();


        testC.invoke();

    }
}
