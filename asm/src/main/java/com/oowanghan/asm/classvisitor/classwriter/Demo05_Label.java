package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.utils.FileUtils;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 *
 * Label 是属于MethodVisitor的方法
 * 在ASM当中，Label类可以用于实现选择（if、switch）、循环（for、while）和try-catch语句。
 * @Author WangHan
 * @Create 2021/8/10 12:36 上午
 */
public class Demo05_Label {

    /**
     * Label类当中的bytecodeOffset字段，就表示当前Instruction“索引值”。
     */
    @Test
    public void generateNewInstance() {

        /**
         * Label类当中的bytecodeOffset字段，就表示当前Instruction“索引值”。
         * 如下 Instruction：
         * 0000: iload_1              // 1B
         * 0001: ifeq            14   // 99000E
         *
         * 0004: getstatic       #2   // B20002     || java/lang/System.out:Ljava/io/PrintStream;
         * 0007: ldc             #3   // 1203       || value is true
         * 0009: invokevirtual   #4   // B60004     || java/io/PrintStream.println:(Ljava/lang/String;)V
         *
         * 0012: goto            11   // A7000B
         *
         * 0015: getstatic       #2   // B20002     || java/lang/System.out:Ljava/io/PrintStream;
         * 0018: ldc             #5   // 1205       || value is false
         * 0020: invokevirtual   #4   // B60004     || java/io/PrintStream.println:(Ljava/lang/String;)V
         *
         * 0023: return               // B1
         *
         * public class HelloWorld {
         *     public void test(boolean flag) {
         *         if (flag) {
         *             System.out.println("value is true");
         *         }
         *         else {
         *             System.out.println("value is false");
         *         }
         *     }
         * }
         *
         * bytecodeOffset用来计算一个“相对偏移量”。
         * 比如说，bytecodeOffset字段的值是15，它标识了getstatic指令的位置，而在索引值为1的位置是ifeq指令，
         * ifeq后面跟的14，这个14就是一个“相对偏移量”。换一个角度来说，由于ifeq的索引位置是1，“相对偏移量”是14，
         * 那么1+14＝15，也就是说，如果ifeq的条件成立，那么下一条执行的指令就是索引值为15的getstatic指令了。
         */
        Label label = new Label();

    }

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
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "test", "(Z)V", null, null);
            Label elseLabel = new Label();      // 首先，准备两个Label对象
            Label returnLabel = new Label();

            mv.visitCode();
            /**
             * test(Z)V
             * [sample/HelloWorld, int] []
             *
             *
             *
             *
             * [] []
             * [sample/HelloWorld, int] [java/io/PrintStream]                      // 注意，这里是“非线性”的变化
             * [sample/HelloWorld, int] [java/io/PrintStream, java/lang/String]
             * [sample/HelloWorld, int] []
             * [] []
             */
            // [sample/HelloWorld, int] [] (boolean类型的在字节码当中其实就是int类型的，0 false, 1 true。byte,short,char,int都会是int类型来处理)
            mv.visitVarInsn(ILOAD, 1);
            // [sample/HelloWorld, int] [int]
            /**
             * 通过MethodVisitor.visitLabel()方法确定label的位置；
             * 通过MethodVisitor类跳转相关的方法（例如，visitJumpInsn()）,跳转到label的位置
             */
            mv.visitJumpInsn(IFEQ, elseLabel);// 如果相同就要跳转到elseLabel的位置
            // [sample/HelloWorld, int] [] （IFEQ默认会把operand stack的栈顶的元素和0做比较）
            // 第1段
            {
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                // [sample/HelloWorld, int] [java/io/PrintStream]
                mv.visitLdcInsn("value is true");
                // [sample/HelloWorld, int] [java/io/PrintStream, java/lang/String]
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                // [sample/HelloWorld, int] []
            }
            mv.visitJumpInsn(GOTO, returnLabel);// 跳出到returnLabel的位置
            // [] [] 这里都为空了， 是因为goto会跳到其他frame的位置，所以这里为空

            // 第2段
            mv.visitLabel(elseLabel);         // 将第一个Label放到这里
            {
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("value is false");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            }

            // 第3段
            mv.visitLabel(returnLabel);      // 将第二个Label放到这里
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
        }

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }

}
