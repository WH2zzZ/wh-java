package com.oowanghan.asm.demo1_create;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 预计生成一个Helloworld
 */
public class HelloWorldDump implements Opcodes {

    public static byte[] dump() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(
                // 1.8
                V1_8,
                // 是public级别
                ACC_PUBLIC | ACC_SUPER,
                // 类的全限定名
                "com/oowanghan/asm/demo1_create/HelloWorld",
                null,
                // 父类
                "java/lang/Object",
                null);

        {
            // 创建一个构造方法
            MethodVisitor mv1 = cw.visitMethod(
                    ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(1, 1);
            mv1.visitEnd();
        }
        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
            mv2.visitCode();
            mv2.visitLdcInsn("This is a HelloWorld object by wanghan.");
            mv2.visitInsn(ARETURN);
            mv2.visitMaxs(1, 1);
            mv2.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}