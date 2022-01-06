package com.oowanghan.asm.classreader.demo06;

import com.oowanghan.asm.classreader.demo05.MethodFindInvokeVisitor;
import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 *
 * @Author WangHan
 * @Create 2021/8/29 6:45 下午
 */
public class HelloWorldTransfer {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/demo06/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);
        ClassVisitor cv = new MethodFindRefVisitor(Opcodes.ASM9, null,
                "com/oowanghan/asm/classreader/demo06/HelloWorld",
                "test", "(III)V");

        classReader.accept(cv, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
    }
}
