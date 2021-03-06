package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;
import com.oowanghan.classfile.lsieun.vs.*;

public class B_ClassFile_Split {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile class_file = ClassFile.parse(bytes);
        String dir_path = FileUtils.getFilePath("split/com.oowanghan.classfile.sample/");
        Visitor v = new FileSplitVisitor(dir_path);
        class_file.accept(v);
        System.out.println("file://" + dir_path);
    }
}
