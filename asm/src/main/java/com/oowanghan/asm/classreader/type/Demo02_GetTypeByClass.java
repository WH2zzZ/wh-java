package com.oowanghan.asm.classreader.type;

import org.objectweb.asm.Type;

public class Demo02_GetTypeByClass {
    public static void main(String[] args) throws Exception {
        Type t = Type.getType(String.class);
        System.out.println(t);
    }
}