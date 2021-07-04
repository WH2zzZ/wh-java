package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.Attributes;
import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.classfile.MethodInfo;
import com.oowanghan.classfile.lsieun.utils.ConstantPoolUtils;
import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.MethodUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;
import com.oowanghan.classfile.lsieun.vs.AttributeStandardVisitor;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public class I_Attributes_Method {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";
        String name_and_type = "test:()V";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile classfile = ClassFile.parse(bytes);
        MethodInfo methodInfo = MethodUtils.findMethod(classfile, name_and_type);

        ConstantPool cp = classfile.constant_pool;
        ConstantPoolUtils.print(cp);

        Attributes attributes = methodInfo.attributes;
        Visitor v = new AttributeStandardVisitor(cp);
        attributes.accept(v);
    }
}
