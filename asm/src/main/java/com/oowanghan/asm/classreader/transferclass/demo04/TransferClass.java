package com.oowanghan.asm.classreader.transferclass.demo04;

import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 *
 * 修改现有的字段。
 *      例如，修改字段的名字、修改字段的类型、修改字段的访问标识，这些需要通过修改visitField()方法的参数来实现。
 * 删除已有的字段。
 *      在visitField()方法中，返回null值，就能够达到删除字段的效果。
 * 添加新的字段。
 *      在visitField()方法中，判断该字段是否已经存在；在visitEnd()方法中，如果该字段不存在，则添加新字段。
 *
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class TransferClass {

    public static void main(String[] args) {
        String relativePath = "com/oowanghan/asm/classreader/transferclass/demo04/HelloWorld.class";
        String filePath = FileUtils.getFilePath(relativePath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader classReader = new ClassReader(bytes);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassAddFieldVisitor addFieldVisitor = new ClassAddFieldVisitor(Opcodes.ASM9, classWriter,
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "objValue", "Ljava/lang/Object;");

        classReader.accept(addFieldVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

        byte[] newBytes = classWriter.toByteArray();
        FileUtils.writeBytes(filePath, newBytes);


    }

}
