package com.oowanghan.asm.classreader.type;

import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.Printer;

public class Demo06_SpecialMethod {

    /**
     * getDimensions()方法，用于获取数组的维度
     * getElementType()方法，用于获取数组的元素的类型
     */
    @Test
    public void test01() throws Exception {
        Type t = Type.getType("[[[[[Ljava/lang/String;");

        int dimensions = t.getDimensions();
        Type elementType = t.getElementType();

        System.out.println(dimensions);    // 5
        System.out.println(elementType);   // Ljava/lang/String;
    }

    /**
     * getArgumentTypes()方法，用于获取“方法”接收的参数类型
     * getReturnType()方法，用于获取“方法”返回值的类型
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Type methodType = Type.getMethodType("(Ljava/lang/String;I)V");

        String descriptor = methodType.getDescriptor();
        Type[] argumentTypes = methodType.getArgumentTypes();
        Type returnType = methodType.getReturnType();

        System.out.println("Descriptor: " + descriptor);
        System.out.println("Argument Types:");
        for (Type t : argumentTypes) {
            System.out.println("    " + t);
        }
        System.out.println("Return Type: " + returnType);
    }

    /**
     * getSize()方法，用于返回某一个类型所占用的slot空间的大小
     * getArgumentsAndReturnSizes()方法，用于返回方法所对应的slot空间的大小
     *
     * 计算方法：
     *
     */
    @Test
    public void test03(){
        Type t = Type.getMethodType("(II)I");
        int value = t.getArgumentsAndReturnSizes();

        // 右移两位 获取入参大小
        int argumentsSize = value >> 2;
        // 取后两位， 获取返回大小
        int returnSize = value & 0b11;

        System.out.println(argumentsSize); // 3
        System.out.println(returnSize);    // 1
    }

    /**
     * getOpcode()方法，会让我们写代码的过程中更加方便。
     *
     * 可以转换相关的操作为正确的参数类型
     */
    @Test
    public void test04() {
        Type t = Type.FLOAT_TYPE;

        int[] opcodes = new int[]{
                Opcodes.IALOAD,
                Opcodes.IASTORE,
                Opcodes.ILOAD,
                Opcodes.ISTORE,
                Opcodes.IADD,
                Opcodes.ISUB,
                Opcodes.IRETURN,
        };

        for (int oldOpcode : opcodes) {
            int newOpcode = t.getOpcode(oldOpcode);

            String oldName = Printer.OPCODES[oldOpcode];
            String newName = Printer.OPCODES[newOpcode];

            System.out.printf("%-7s --- %-7s%n", oldName, newName);
        }
    }
}