package com.oowanghan.asm.classreader.transferclass.demo06;

import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.*;

/**
 *
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class TransferClass {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/transferclass/demo06/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassVisitor cv = new ClassAddMethodVisitor(Opcodes.ASM9, classWriter, Opcodes.ACC_PUBLIC, "mul", "(II)I", null, null) {
            @Override
            protected void generateMethodBody(MethodVisitor mv) {
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ILOAD, 2);
                mv.visitInsn(Opcodes.IMUL);
                mv.visitInsn(Opcodes.IRETURN);
                mv.visitMaxs(2, 3);
                mv.visitEnd();
            }
        };

        classReader.accept(cv, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

        byte[] newBytes = classWriter.toByteArray();
        FileUtils.writeBytes(filePath, newBytes);


    }

}
