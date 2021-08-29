package com.oowanghan.asm.classreader.transferclass.demo02;

import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * 在HelloWorld类中，定义了一个clone()方法，但存在一个问题，也就是，
 * 如果没有实现Cloneable接口，clone()方法就会出错，我们的目标是希望通过ASM为HelloWorld类添加上Cloneable接口。
 *
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class TransferClass {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/transferclass/demo02/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassCloneVisitor classCloneVisitor = new ClassCloneVisitor(Opcodes.ASM9, classWriter);

        classReader.accept(classCloneVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

        byte[] newBytes = classWriter.toByteArray();
        FileUtils.writeBytes(filePath, newBytes);


    }

}
