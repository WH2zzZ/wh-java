package com.wanghan.java8.feature.interface_method;

import org.junit.jupiter.api.Test;

public class MethodTest {
    @Test
    public void testGetName(){
        MethodDemo methodDemo = new MethodDemo();

        System.out.println(methodDemo.getName());
    }

    @Test
    public void testStaticMethod(){
        InterfaceDemo.show();
    }
}
