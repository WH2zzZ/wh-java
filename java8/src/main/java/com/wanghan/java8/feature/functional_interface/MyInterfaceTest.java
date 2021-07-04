package com.wanghan.java8.feature.functional_interface;

import org.junit.jupiter.api.Test;

/**
 * @Author WangHan
 * @Create 2021/6/19 3:45 下午
 */
public class MyInterfaceTest {

    public static void print(Integer a){

    }

    @Test
    public void test1(){
        MyFunctionInterface <String, String> myFunctionInterface = s -> s + s;
    }
}
