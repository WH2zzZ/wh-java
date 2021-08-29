package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.utils.FileUtils;
import com.oowanghan.asm.utils.HexUtils;
import org.junit.Test;
import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 *
 * @Author WangHan
 * @Create 2021/8/10 12:36 上午
 */
public class Demo03_MethodWriter {


    /**
     * 调用顺序：
     * (visitParameter)*
     * [visitAnnotationDefault]
     * (visitAnnotation | visitAnnotableParameterCount | visitParameterAnnotation | visitTypeAnnotation | visitAttribute)*
     * [
     *     visitCode
     *     (
     *         visitFrame |
     *         visitXxxInsn |
     *         visitLabel |
     *         visitInsnAnnotation |
     *         visitTryCatchBlock |
     *         visitTryCatchAnnotation |
     *         visitLocalVariable |
     *         visitLocalVariableAnnotation |
     *         visitLineNumber
     *     )*
     *     visitMaxs
     * ]
     * visitEnd
     *
     * 第一组，在visitCode()方法之前的方法。这一组的方法，主要负责parameter、annotation和attributes等内容；
     * 第二组，在visitCode()方法和visitMaxs()方法之间的方法。这一组的方法，主要负责当前方法的“方法体”内的opcode内容。
     *      visitCode()方法，标志着方法体的开始，而visitMaxs()方法，标志着方法体的结束。
     *      第一步，调用visitCode()方法，调用一次。
     *      第二步，调用visitXxxInsn()方法，可以调用多次。对这些方法的调用，就是在构建方法的“方法体”。
     *      第三步，调用visitMaxs()方法，调用一次。
     *      第四步，调用visitEnd()方法，调用一次。
     * 第三组，是visitEnd()方法。这个visitEnd()方法，是最后一个进行调用的方法。
     * @throws IOException
     */

    /**
     * 生成<init>方法
     *
     * 对于HelloWorld类中<init>()方法对应的Instruction内容如下：
     *
     * public sample.HelloWorld();
     *   Code:
     *      0: aload_0
     *      1: invokespecial #9                  // Method java/lang/Object."<init>":()V
     *      4: return
     *
     * 该方法对应的Frame变化情况如下：
     *
     * <init>()V
     * [uninitialized_this] []
     * [uninitialized_this] [uninitialized_this]
     * [sample/HelloWorld] []
     * [] []
     * 在这里，我们看到一个很“不一样”的变量，就是uninitialized_this，它就是一个“引用”，它指向的内存空间还没有初始化；
     * 等经过初始化之后，uninitialized_this变量就变成this变量。
     */
    @Test
    public void init() {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER,
                "sample/HelloWorld", null, "java/lang/Object", null);

        // <init>表示参构造方法名字
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        // 等价于 super();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }

    /**
     * clinit方法 class initialization method
     * public class sample.HelloWorld {
     *   public {};
     *     Code:
     *        0: getstatic     #12                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *        3: ldc           #14                 // String class initialization method
     *        5: invokevirtual #20                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *        8: return
     * }
     *
     */
    @Test
    public void clinit() {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER,
                "sample/HelloWorld", null, "java/lang/Object", null);

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<clinit>", "()V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("class initialization method");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 0);
        mv.visitEnd();

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }

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
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            mv2.visitCode();

            // 创建一个对象, 并将其引用引用值压入栈顶
            mv2.visitTypeInsn(NEW, "com/oowanghan/asm/classwriter/generate/GoodChild");
//            [sample/HelloWorld] [uninitialized_sample/GoodChild]

            // 拷贝一个GoodChild
            // 如果不使用 dup 复制，被构造函数指令使用后，最终无法返回实例引用。
            mv2.visitInsn(DUP); //dup指令可以复制栈顶的一个字再压入栈,也就是把栈顶的内容做个备份
//            [sample/HelloWorld] [uninitialized_sample/GoodChild, uninitialized_sample/GoodChild]

            // 载入一个string到operand stack中
            mv2.visitLdcInsn("Lucy");
//            [sample/HelloWorld] [uninitialized_sample/GoodChild, uninitialized_sample/GoodChild, java/lang/String]

            // 载入一个int 8 到 operand stack中
            mv2.visitIntInsn(BIPUSH, 8);
//            [sample/HelloWorld] [uninitialized_sample/GoodChild, uninitialized_sample/GoodChild, java/lang/String, int]

            // 调用初始化方法， 从栈中取出需要的数据
            mv2.visitMethodInsn(INVOKESPECIAL, "com/oowanghan/asm/classwriter/generate/GoodChild", "<init>", "(Ljava/lang/String;I)V", false);
//            [sample/HelloWorld] [sample/GoodChild]

            // stack顶的元素存储到local
            mv2.visitVarInsn(ASTORE, 1);
//            [sample/HelloWorld, sample/GoodChild] []

            mv2.visitInsn(RETURN);
            mv2.visitMaxs(4, 2);
            mv2.visitEnd();
        }

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        String filePath = FileUtils.getFilePath("sample/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }

}
