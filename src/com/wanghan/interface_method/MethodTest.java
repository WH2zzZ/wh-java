package com.wanghan.interface_method;

import org.junit.Test;

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
