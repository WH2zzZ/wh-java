package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.utils.FileUtils;
import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * @Author WangHan
 * @Create 2021/8/12 11:00 下午
 */
public class Demo02_FieldWriter {

    @Test
    public void generateField(){
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_INTERFACE, "sample/HelloWorld",
                null, "java/lang/Object", null);

        FieldVisitor fv = cw.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL,
                "param1", "Ljava/lang/String;", null, "");
        AnnotationVisitor annotationVisitor = fv.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", true);
        annotationVisitor.visit("value", "asm");
        annotationVisitor.visitEnd();
        fv.visitEnd();

        byte[] bytes = cw.toByteArray();
        String realPath = "sample/HelloWorld.class";
        String filePath = FileUtils.getFilePath(realPath);
        FileUtils.writeBytes(filePath, bytes);

    }
}
