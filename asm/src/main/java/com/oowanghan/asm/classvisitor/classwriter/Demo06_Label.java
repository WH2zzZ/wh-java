package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.utils.FileUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 *
 * Label 代码案例
 * @Author WangHan
 * @Create 2021/8/10 12:36 上午
 */
public class Demo06_Label {

    /**
     * public class HelloWorld {
     *     public void test(int value) {
     *         if (value == 0) {
     *             System.out.println("value is 0");
     *         }
     *         else {
     *             System.out.println("value is not 0");
     *         }
     *     }
     * }
     */
    @Test
    public void generateIf() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/HelloWorld", null, "java/lang/Object", null);

        // 构造函数
        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(0,0);
            mv1.visitEnd();
        }

        /**
         * public void test(int value) {
         *     if (value == 0) {
         *         System.out.println("value is 0");
         *     }
         *     else {
         *         System.out.println("value is not 0");
         *     }
         * }
         */
        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "(I)V", null, null);
            Label elseLabel = new Label();
            Label returnLabel = new Label();

            mv2.visitCode();
            mv2.visitVarInsn(ILOAD, 1);
            // 只有false的时候才会跳到else里面去
            mv2.visitJumpInsn(IFEQ, elseLabel);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("value is 0");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            mv2.visitLabel(elseLabel);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("value is not 0");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            mv2.visitLabel(returnLabel);
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(0, 0);
            mv2.visitEnd();
        }

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);
    }

    /**
     * MethodVisitor.visitTableSwitchInsn()方法，也可以使用MethodVisitor.visitLookupSwitchInsn()方法。
     *
     * public class HelloWorld {
     *     public void test(int val) {
     *         switch (val) {
     *             case 1:
     *                 System.out.println("val = 1");
     *                 break;
     *             case 2:
     *                 System.out.println("val = 4");
     *                 break;
     *             default:
     *                 System.out.println("val is unknown");
     *         }
     *     }
     * }
     */
    @Test
    public void generateSwitch() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/HelloWorld", null, "java/lang/Object", null);

        // 构造函数
        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(0,0);
            mv1.visitEnd();
        }

        /**
         * public void test(int val) {
         *     switch (val) {
         *         case 1:
         *             System.out.println("val = 1");
         *             break;
         *         case 2:
         *             System.out.println("val = 4");
         *             break;
         *         default:
         *             System.out.println("val is unknown");
         *     }
         * }
         */
        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "(I)V", null, null);
            Label caseLabel1 = new Label();
            Label caseLabel2 = new Label();
            Label defaultLabel = new Label();
            Label returnLabel = new Label();

            mv2.visitCode();
            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitTableSwitchInsn(1, 2, defaultLabel, caseLabel1, caseLabel2);

            mv2.visitLabel(caseLabel1);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("value is 1");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            mv2.visitLabel(caseLabel2);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("value is 2");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            mv2.visitLabel(defaultLabel);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("value is default");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            mv2.visitLabel(returnLabel);
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(0, 0);
            mv2.visitEnd();
        }
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);
    }

    /**
     * public class HelloWorld {
     *     public void test() {
     *         for (int i = 0; i < 10; i++) {
     *             System.out.println(i);
     *         }
     *     }
     * }
     */
    @Test
    public void generateFor() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/HelloWorld", null, "java/lang/Object", null);

        // 构造函数
        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(0,0);
            mv1.visitEnd();
        }

        /**
         * public void test() {
         *     for (int i = 0; i < 10; i++) {
         *         System.out.println(i);
         *     }
         * }
         */
        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            Label conditionLabel = new Label();
            Label returnLabel = new Label();

            mv2.visitCode();
            mv2.visitInsn(ICONST_0);
            mv2.visitVarInsn(ISTORE, 1);

            mv2.visitLabel(conditionLabel);
            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitIntInsn(BIPUSH, 10);
            mv2.visitJumpInsn(IF_ICMPGE, returnLabel);

            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv2.visitIincInsn(1, 1);
            mv2.visitJumpInsn(GOTO, conditionLabel);

            mv2.visitLabel(returnLabel);
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(0, 0);
            mv2.visitEnd();
        }
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);
    }

    /**
     * public class HelloWorld {
     *     public void test() {
     *         try {
     *             System.out.println("Before Sleep");
     *             Thread.sleep(1000);
     *             System.out.println("After Sleep");
     *         } catch (InterruptedException e) {
     *             e.printStackTrace();
     *         }
     *     }
     * }
     */
    @Test
    public void generateTryCatch() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/HelloWorld", null, "java/lang/Object", null);

        // 构造函数
        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(0,0);
            mv1.visitEnd();
        }

        /**
         * public void test() {
         *     try {
         *         System.out.println("Before Sleep");
         *         Thread.sleep(1000);
         *         System.out.println("After Sleep");
         *     } catch (InterruptedException e) {
         *         e.printStackTrace();
         *     }
         * }
         */
        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            Label startLabel = new Label();
            Label endLabel = new Label();
            Label handlerLabel = new Label();
            Label returnLabel = new Label();

            mv2.visitCode();
            // visitTryCatchBlock可以在这里访问
            mv2.visitTryCatchBlock(startLabel, endLabel, handlerLabel, "java/lang/InterruptedException");

            {
                mv2.visitLabel(startLabel);

                mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv2.visitLdcInsn("Before Sleep");
                mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                mv2.visitLdcInsn(1000L);
                mv2.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "sleep", "(J)V", false);

                mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv2.visitLdcInsn("After Sleep");
                mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                mv2.visitLabel(endLabel);
                mv2.visitJumpInsn(GOTO, returnLabel);
            }

            {
                mv2.visitLabel(handlerLabel);
                mv2.visitVarInsn(ASTORE, 1);
                mv2.visitVarInsn(ALOAD, 1);
                mv2.visitMethodInsn(INVOKEVIRTUAL, "java/lang/InterruptedException", "printStackTrace", "()V", false);
            }

            mv2.visitLabel(returnLabel);
            mv2.visitInsn(RETURN);
            // visitTryCatchBlock可以在这里访问
//            mv2.visitTryCatchBlock(startLabel, endLabel, handlerLabel, "java/lang/InterruptedException");
            mv2.visitMaxs(0, 0);
            mv2.visitEnd();
        }
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);
    }

}
