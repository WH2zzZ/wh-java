package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.classfile.ClassInfo;
import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.utils.ConstantPoolUtils;
import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;
import com.oowanghan.classfile.lsieun.vs.*;

public class F_ClassInfo {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile classfile = ClassFile.parse(bytes);

        ConstantPool cp = classfile.constant_pool;
        ConstantPoolUtils.print(cp);

        ClassInfo class_info = classfile.class_info;
        Visitor class_info_visitor = new ClassFileStandardVisitor();
        class_info.accept(class_info_visitor);
    }
}
