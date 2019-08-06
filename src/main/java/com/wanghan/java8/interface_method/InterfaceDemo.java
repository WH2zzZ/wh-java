package com.wanghan.java8.interface_method;

/**
 * 接口中的默认方法与静态方法
 */
public interface InterfaceDemo {

    default String getName(){
        return "接口方法的默认方法";
    }

    static void show(){
        System.out.println("接口的静态方法");
    }
}
