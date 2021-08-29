package com.oowanghan.asm.classreader.type;

import org.objectweb.asm.Type;

public class Demo04_GetTypeByInternalName {
    public static void main(String[] args) throws Exception {
        Type t = Type.getObjectType("java/lang/String");
        System.out.println(t);
    }
}