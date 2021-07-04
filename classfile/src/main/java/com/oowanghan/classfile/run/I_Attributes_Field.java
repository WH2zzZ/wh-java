package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.Attributes;
import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.classfile.FieldInfo;
import com.oowanghan.classfile.lsieun.utils.ConstantPoolUtils;
import com.oowanghan.classfile.lsieun.utils.FieldUtils;
import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;
import com.oowanghan.classfile.lsieun.vs.AttributeStandardVisitor;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public class I_Attributes_Field {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";
        String name_and_type = "intValue:I";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile classfile = ClassFile.parse(bytes);
        FieldInfo field_info = FieldUtils.findField(classfile, name_and_type);

        ConstantPool cp = classfile.constant_pool;
        ConstantPoolUtils.print(cp);

        Attributes attributes = field_info.attributes;
        Visitor v = new AttributeStandardVisitor(cp);
        attributes.accept(v);
    }
}
