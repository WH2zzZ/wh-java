package com.oowanghan.asm.classreader.transferclass.demo01;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Author WangHan
 * @Create 2021/8/23 11:39 下午
 */
public class ClassChangeVersionVisitor extends ClassVisitor {
    public ClassChangeVersionVisitor(int api) {
        super(api);
    }

    public ClassChangeVersionVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(Opcodes.V1_7, access, name, signature, superName, interfaces);
    }
}
