package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.classfile.ClassFile;
import com.oowanghan.classfile.lsieun.classfile.MethodInfo;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.utils.InstructionChain;
import com.oowanghan.classfile.lsieun.code.visitors.BasicOpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeReadVisitor;
import com.oowanghan.classfile.lsieun.utils.*;

public class J_Basic_Opcode {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";
        String name_and_type = "test:()V";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        ClassFile classfile = ClassFile.parse(bytes);
        process(classfile, name_and_type);
    }

    public static void process(ClassFile classfile, String name_and_type) {
        MethodInfo method_info = MethodUtils.findMethod(classfile, name_and_type);

        byte[] code_bytes = AttributeUtils.findCodeAttribute(method_info).code;
        System.out.println("=== === ===  === === ===  === === ===");
        System.out.println("HexCode:");
        System.out.println(HexUtils.format(code_bytes, HexFormat.FORMAT_FF_SPACE_FF_16));
        System.out.println("=== === ===  === === ===  === === ===");

        OpcodeReadVisitor rv = new OpcodeReadVisitor(code_bytes);
        InstructionChain chain = rv.getInstructionChain();

        BasicOpcodeVisitor v = new BasicOpcodeVisitor(code_bytes);
        Instruction current = chain.start;
        while(current != null) {
            current.accept(v);
            current = current.next;
        }
    }
}
