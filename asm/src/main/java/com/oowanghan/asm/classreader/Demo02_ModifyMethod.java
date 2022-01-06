package com.oowanghan.asm.classreader;

import org.junit.Test;

import java.io.IOException;

/**
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class Demo02_ModifyMethod {

    @Test
    public void modifyEnterMethod() throws IOException {
        /**
         * public void visitCode() {
         *     // 首先，处理自己的代码逻辑
         *     //  添加“方法进入”时的代码
         *
         *     // 其次，调用父类的方法实现
         *     super.visitCode();
         * }
         */
    }

    @Test
    public void modifyExitMethod() throws IOException {
        /**
         * public void visitInsn(int opcode) {
         *     // 首先，处理自己的代码逻辑
         *     if (opcode == Opcodes.ATHROW || (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
         *         //  添加“方法退出”时的代码
         *     }
         *
         *     // 其次，调用父类的方法实现
         *     super.visitInsn(opcode);
         * }
         */
    }
}
