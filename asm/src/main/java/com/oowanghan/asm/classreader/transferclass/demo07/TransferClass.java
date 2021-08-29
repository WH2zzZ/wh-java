package com.oowanghan.asm.classreader.transferclass.demo07;

import com.oowanghan.asm.classreader.transferclass.demo06.ClassAddMethodVisitor;
import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.*;

/**
 *
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class TransferClass {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/transferclass/demo07/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassVisitor cv1 = new ClassVisitor1(Opcodes.ASM9, classWriter, 1);
        ClassVisitor cv2 = new ClassVisitor1(Opcodes.ASM9, cv1, 2);
        ClassVisitor cv3 = new ClassVisitor1(Opcodes.ASM9, cv2, 3);

        classReader.accept(cv3, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

        byte[] newBytes = classWriter.toByteArray();
        FileUtils.writeBytes(filePath, newBytes);


    }

}
