package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;
import com.oowanghan.classfile.lsieun.vs.*;

public class B_ClassFile_Raw {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile classfile = ClassFile.parse(bytes);
        // 可以使用 ClassFileRawVisitor、ClassFileSimpleVisitor 或者 ClassFileStandardVisitor
//        Visitor v = new ClassFileRawVisitor();
        Visitor v = new ClassFileStandardVisitor();
        classfile.accept(v);
    }
}
