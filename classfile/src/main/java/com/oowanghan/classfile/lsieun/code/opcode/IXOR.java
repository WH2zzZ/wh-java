package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

/**
 * IXOR - Bitwise XOR int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class IXOR extends Instruction implements ArithmeticInstruction {

    public IXOR() {
        super(OpcodeConst.IXOR, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIXOR(this);
    }

}

