package com.oowanghan.asm.classreader.demo01;

import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @Author WangHan
 * @Create 2021/8/29 6:45 下午
 */
public class HelloWorldTransfer {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/demo01/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        MethodEnterVisitor methodEnterVisitor = new MethodEnterVisitor(Opcodes.ASM9, classWriter);

        classReader.accept(methodEnterVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        byte[] newBytes = classWriter.toByteArray();

        FileUtils.writeBytes(filePath, newBytes);

    }
}
