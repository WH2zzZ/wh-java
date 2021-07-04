package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

/**
 * ISUB - Substract ints
 * <PRE>Stack: ..., value1, value2 -&gt; result</PRE>
 */
public class ISUB extends Instruction implements ArithmeticInstruction {

    public ISUB() {
        super(OpcodeConst.ISUB, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitISUB(this);
    }

}
