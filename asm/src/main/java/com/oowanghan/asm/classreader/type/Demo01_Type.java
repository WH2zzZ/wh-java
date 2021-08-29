package com.oowanghan.asm.classreader.type;

import org.objectweb.asm.Type;

public class Demo01_Type {
    public static void main(String[] args) throws Exception {
        Type t = Type.getType("Ljava/lang/String;");

        int sort = t.getSort();                    // ASM
        String className = t.getClassName();       // Java File
        String internalName = t.getInternalName(); // Class File
        String descriptor = t.getDescriptor();     // Class File

        System.out.println(sort);         // 10，它对应于Type.OBJECT字段
        System.out.println(className);    // java.lang.String   注意，分隔符是“.”
        System.out.println(internalName); // java/lang/String   注意，分隔符是“/”
        System.out.println(descriptor);   // Ljava/lang/String; 注意，分隔符是“/”，前有“L”，后有“;”
    }
}