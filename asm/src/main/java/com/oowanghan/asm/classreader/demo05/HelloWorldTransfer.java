package com.oowanghan.asm.classreader.demo05;

import com.oowanghan.asm.classreader.demo04.MethodEmptyBodyVisitor;
import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 *
 * 清空方法体，mock方法返回
 * @Author WangHan
 * @Create 2021/8/29 6:45 下午
 */
public class HelloWorldTransfer {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/demo05/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);

        MethodFindInvokeVisitor methodFindInvokeVisitor = new MethodFindInvokeVisitor(Opcodes.ASM9, null, "test", "(II)V");

        classReader.accept(methodFindInvokeVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
    }
}
