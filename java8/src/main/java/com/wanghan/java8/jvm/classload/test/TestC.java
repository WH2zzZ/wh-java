package com.wanghan.java8.jvm.classload.test;

/**
 * TODO
 *
 * @className: TestC
 * @author: wanghan
 * @date: 2022-01-25 20:51
 **/
public class TestC {


    public void invoke() {
        System.out.println("TestC invoke");
        System.out.println("TestC classloader : " + this.getClass().getClassLoader());
        TestA.invoke();
    }
}
