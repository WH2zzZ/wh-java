package com.oowanghan.asm.sample.test;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法调用trace类
 * @Author WangHan
 * @Create 6:12 下午 2021/9/12
 */
public class MethodInvokeTraceVisitor extends ClassVisitor {

    private final Logger log = LoggerFactory.getLogger(MethodInvokeTraceVisitor.class);

    private final boolean isMatcher = true;

    public MethodInvokeTraceVisitor(int api, ClassVisitor classVisitor, String pattern) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (isMatcher) {
            return super.visitAnnotation(descriptor, visible);
        }
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (isMatcher){
            String methodMetaInfo = name;
            return new MethodFindInvokeAdapter(api, null, methodMetaInfo);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    private static class MethodFindInvokeAdapter extends MethodVisitor {
        private final Logger log = LoggerFactory.getLogger(MethodFindInvokeAdapter.class);
        private final List<String> list = new ArrayList<>();

        private final String currentMethod;
        private int currentInvokeIndex = 0;

        public MethodFindInvokeAdapter(int api, MethodVisitor methodVisitor, String methodMetaInfo) {
            super(api, methodVisitor);
            this.currentMethod = methodMetaInfo;
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            String info = String.format("%s %s.%s%s", Printer.OPCODES[opcode], owner, name, descriptor);
            log.info("[trace-info] message str : {}", info);
        }

        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }
}