package com.wanghan.java8.jvm.classload.test;

/**
 * TODO
 *
 * @className: TestB
 * @author: wanghan
 * @date: 2022-01-25 20:50
 **/
public class TestB extends TestA.Parent {
    @Override
    public void invoke() {
        System.out.println("TestB invoke");
        System.out.println("TestB ClassLoader : " + this.getClass().getClassLoader());
    }
}
