package com.oowanghan.classfile.lsieun.utils;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.vs.*;

public class ConstantPoolUtils {
    public static void print(ConstantPool cp) {
        print(cp, false);
    }

    public static void print(ConstantPool cp, boolean raw) {
        if (!raw) {
            cp.simplify();
        }

        Visitor v = new ClassFileStandardVisitor();
        cp.accept(v);
    }
}
