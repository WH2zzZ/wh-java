package com.wanghan.java8.jvm.classload.test;

/**
 * TODO
 *
 * @className: Test1
 * @author: wanghan
 * @date: 2022-01-25 20:50
 **/
public class TestA {

    private static Parent INVOKER = new EmptyParent();

    public static void setInvoker(Parent parent) {
        INVOKER = parent;
    }

    public static void invoke() {
        System.out.println("TestA ClassLoader : " + TestA.class.getClassLoader());
        INVOKER.invoke();
    }

    public static abstract class Parent{
        public abstract void invoke();
    }

    static class EmptyParent extends Parent{

        @Override
        public void invoke() {
            System.out.println("EmptyParent invoke");
        }
    }
}
