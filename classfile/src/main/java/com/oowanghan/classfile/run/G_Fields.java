package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.classfile.Fields;
import com.oowanghan.classfile.lsieun.utils.ConstantPoolUtils;
import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;
import com.oowanghan.classfile.lsieun.vs.*;

public class G_Fields {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile classfile = ClassFile.parse(bytes);
        process(classfile);
    }

    public static void process(ClassFile classfile) {
        ConstantPool cp = classfile.constant_pool;
        ConstantPoolUtils.print(cp);

        Fields fields = classfile.fields;
        // 可以使用 ClassFileSimpleVisitor 或者 ClassFileStandardVisitor
        Visitor v = new ClassFileStandardVisitor();
        fields.accept(v);
    }
}
