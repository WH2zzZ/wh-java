package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.core.ClassChangeVersionVisitor;
import com.oowanghan.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class HelloWorldTransformCore {
    public static void main(String[] args) {
        String relative_path = "com/oowanghan/asm/sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes1 = FileUtils.readBytes(filepath);
        if (bytes1 == null) {
            throw new RuntimeException("bytes1 is null");
        }

        //（1）构建ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建ClassWriter
        // 使用这种方式会造成字节码文件的数据冗余
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

        //（3）串连ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new ClassChangeVersionVisitor(api, cw);


        //（4）结合ClassReader和ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);
    }
}
