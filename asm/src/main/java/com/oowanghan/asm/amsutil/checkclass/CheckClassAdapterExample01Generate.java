package com.oowanghan.asm.amsutil.checkclass;

import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;

import static org.objectweb.asm.Opcodes.*;

/**
 * 在生成类（Class Generation）或转换类（Class Transformation）的过程中进行检查
 */
public class CheckClassAdapterExample01Generate {
    /**
     * 第一步，应用于Class Generation
     * 串联ClassVisitor：cv --- cca --- cw
     * ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
     * CheckClassAdapter cca = new CheckClassAdapter(cw);
     * ClassVisitor cv = new MyClassVisitor(cca);
     *
     * 第二步，应用于Class Transformation
     * byte[] bytes = ... // 这里是class file bytes
     * ClassReader cr = new ClassReader(bytes);
     * cr.accept(cv, 0);
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String relative_path = "sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);

        // (1) 生成byte[]内容
        byte[] bytes = dump();

        // (2) 保存byte[]到文件
        FileUtils.writeBytes(filepath, bytes);
    }

    public static byte[] dump() throws Exception {
        // (1) 创建ClassWriter对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new CheckClassAdapter(cw);

        // (2) 调用visitXxx()方法
        cv.visit(V1_8, ACC_PUBLIC + ACC_SUPER, "sample/HelloWorld",
                null, "java/lang/Object", null);

        {
            FieldVisitor fv = cv.visitField(ACC_PRIVATE, "intValue", "I", null, null);
            fv.visitEnd();
        }

        {
            MethodVisitor mv1 = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(1, 1);
            mv1.visitEnd();
        }

        {
            MethodVisitor mv2 = cv.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            mv2.visitCode();
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("Hello World");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(2, 1);
            mv2.visitEnd();
        }
        cv.visitEnd();

        // (3) 调用toByteArray()方法
        return cw.toByteArray();
    }
}