package com.oowanghan.asm.classreader.demo03;

import com.oowanghan.asm.classreader.demo02.MethodParameterVisitor;
import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * 1. 接口，是否需要处理？接口当中的抽象方法没有方法体，但也可能有带有方法体的default方法。
 * 2. 带有特殊修饰符的方法：
 *      抽象方法，是否需要处理？不只是接口当中有抽象方法，抽象类里也可能有抽象方法。抽象方法，是没有方法体的。
 *      native方法，是否需要处理？native方法是没有方法体的。
 * 3. 名字特殊的方法，例如，构造方法（<init>()）和静态初始化方法（<clinit>()），是否需要处理？
 *
 * @Author WangHan
 * @Create 2021/8/29 6:45 下午
 */
public class HelloWorldTransfer {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/demo03/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        MethodTimerVisitor methodTimerVisitor = new MethodTimerVisitor(Opcodes.ASM9, classWriter);

        classReader.accept(methodTimerVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        byte[] newBytes = classWriter.toByteArray();

        FileUtils.writeBytes(filePath, newBytes);

    }
}
