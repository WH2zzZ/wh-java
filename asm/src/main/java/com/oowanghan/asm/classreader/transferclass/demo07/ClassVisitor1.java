package com.oowanghan.asm.classreader.transferclass.demo07;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @Author WangHan
 * @Create 2021/8/25 11:16 下午
 */
public class ClassVisitor1 extends ClassVisitor {

    private Integer no;

    public ClassVisitor1(int api, ClassVisitor classVisitor, Integer no) {
        super(api, classVisitor);
        this.no = no;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(no + " visit run");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println(no + " visitField run");
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println(no + " visitMethod run");
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        System.out.println(no + " visitEnd run");
        super.visitEnd();
    }
}
