package com.oowanghan.asm.classreader.demo01;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.GETSTATIC;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * @Author WangHan
 * @Create 2021/8/29 6:19 下午
 */
public class MethodEnterVisitor extends ClassVisitor {

    public MethodEnterVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        // 判断是否为构造函数
        if (mv != null && !"<init>".equals(name)) {
            mv = new MethodEnterAdapter(api, mv);
        }
        return mv;
    }

    private static class MethodEnterAdapter extends MethodVisitor {

        public MethodEnterAdapter(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public void visitCode() {
            // 添加自己的处理逻辑
            super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            super.visitLdcInsn("Method Enter...");
            super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }
}
