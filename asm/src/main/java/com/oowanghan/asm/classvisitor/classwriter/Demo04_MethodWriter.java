package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.utils.FileUtils;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 *
 * @Author WangHan
 * @Create 2021/8/10 12:36 上午
 */
public class Demo04_MethodWriter {

    /**
     * 预期目标
     * public class HelloWorld {
     *     public void test(int a, int b) {
     *         int val = Math.max(a, b); // 对static方法进行调用
     *         System.out.println(val);  // 对non-static方法进行调用
     *     }
     * }
     */
    @Test
    public void generateNewInstance() {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER,
                "sample/HelloWorld", null, "java/lang/Object", null);

        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(1, 1);
            mv1.visitEnd();
        }

        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "(II)V", null, null);
            mv2.visitCode();

            /**
             * public void test(int, int);
             *   Code:
             *      0: iload_1
             *      1: iload_2
             *      2: invokestatic  #21                 // Method java/lang/Math.max:(II)I
             *      5: istore_3
             *      6: getstatic     #27                 // Field java/lang/System.out:Ljava/io/PrintStream;
             *      9: iload_3
             *     10: invokevirtual #33                 // Method java/io/PrintStream.println:(I)V
             *     13: return
             */
            // 0: iload_1
            mv2.visitVarInsn(ILOAD, 1);
            // iload_2
            mv2.visitVarInsn(ILOAD, 2);

            // 调用静态方法， operand stack里面不需要Math的实例
            // 2: invokestatic  #21                 // Method java/lang/Math.max:(II)I
            mv2.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "max", "(II)I", false);
            // 5: istore_3
            mv2.visitVarInsn(ISTORE, 3);

            // 这里的方法调用还会消耗掉 一个PrintStream的实例
            // 6: getstatic     #27                 // Field java/lang/System.out:Ljava/io/PrintStream;
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // 9: iload_3
            mv2.visitVarInsn(ILOAD, 3);
            // 10: invokevirtual #33                 // Method java/io/PrintStream.println:(I)V
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            // 13: return
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(2, 4);
            mv2.visitEnd();
        }

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }

    /**
     * 不调用visitMaxs()方法
     */
    @Test
    public void generateNewInstance_noVisitMaxs() {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER,
                "sample/HelloWorld", null, "java/lang/Object", null);

        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(1, 1);
            mv1.visitEnd();
        }

        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "(II)V", null, null);
            mv2.visitCode();
            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitVarInsn(ILOAD, 2);
            mv2.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "max", "(II)I", false);
            mv2.visitVarInsn(ISTORE, 3);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitVarInsn(ILOAD, 3);
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv2.visitInsn(RETURN);

            /**
             * 第1种情况，在创建ClassWriter对象时，flags参数使用ClassWriter.COMPUTE_FRAMES值，在调用mv.visitMaxs(0, 0)方法之后，仍然能得到一个正确的.class文件。
             * 第2种情况，在创建ClassWriter对象时，flags参数使用0值，在调用mv.visitMaxs(0, 0)方法之后，得到的.class文件就不能正确运行。
             * 需要注意的是，在创建ClassWriter对象时，flags参数使用ClassWriter.COMPUTE_FRAMES值，我们可以给visitMaxs()方法传入一个错误的值，但是不能省略对于visitMaxs()方法的调用。如果我们省略掉visitCode()和visitEnd()方法，生成的.class文件也不会出错；当然，并不建议这么做。但是，如果我们省略掉对于visitMaxs()方法的调用，生成的.class文件就会出错。
             *
             * 如果省略对于visitMaxs()方法的调用，会出现如下错误：
             *
             * Exception in thread "main" java.lang.VerifyError: Operand stack overflow
             */
            mv2.visitMaxs(2, 4);
            mv2.visitEnd();
        }

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }

}
