package com.oowanghan.asm.classreader.transferclass.demo03;

import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 *
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class TransferClass {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/transferclass/demo03/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassRemoveFieldVisitor classRemoveFieldVisitor = new ClassRemoveFieldVisitor(Opcodes.ASM9, classWriter,
                "strValue", "Ljava/lang/String;");

        classReader.accept(classRemoveFieldVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

        byte[] newBytes = classWriter.toByteArray();
        FileUtils.writeBytes(filePath, newBytes);


    }

}
