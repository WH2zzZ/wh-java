package com.oowanghan.asm.asmprint;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1. ASMPrint类，它能帮助我们将.class文件转换为ASM代码
 * 2. 利用org.objectweb.asm.util.TraceClassVisitor类来实现
 * @Author WangHan
 * @Create 2021/7/5 10:39 下午
 */
public class Demo01 {

    /**
     * className值 为类的全限定名
     * asmCode值 为true或false
     *      如果是true，可以打印出对应的ASM代码；
     *      如果是false，可以打印出方法对应的字节码Instruction。
     * parsingOptions值 为
     *      ClassReader.SKIP_CODE
     *      ClassReader.SKIP_DEBUG
     *      ClassReader.SKIP_FRAMES
     *      ClassReader.EXPAND_FRAMES的组合值，也可以设置为0，可以打印出详细程度不同的信息。
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 设置参数
        String classname = "com.oowanghan.asm.classreader.demo01.HelloWorld";
        int parsingOptions = ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG;
//        boolean asmCode = false;
        boolean asmCode = true;

        // 打印结果
        Printer printer = asmCode ? new ASMifier() : new Textifier();
        PrintWriter printWriter = new PrintWriter(System.out, true);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(classname).accept(traceClassVisitor, parsingOptions);
    }
}
