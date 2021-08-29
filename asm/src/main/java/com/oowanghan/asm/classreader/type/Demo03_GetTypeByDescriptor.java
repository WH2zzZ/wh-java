package com.oowanghan.asm.classreader.type;

import org.objectweb.asm.Type;

public class Demo03_GetTypeByDescriptor {
    public static void main(String[] args) throws Exception {
        Type t1 = Type.getType("Ljava/lang/String;");
        System.out.println(t1);

        // 这里是方法的描述符
        Type t2 = Type.getMethodType("(II)I");
        System.out.println(t2);
    }
}