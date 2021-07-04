package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

/**
 * ISHL - Arithmetic shift left int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class ISHL extends Instruction implements ArithmeticInstruction {

    public ISHL() {
        super(OpcodeConst.ISHL, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitISHL(this);
    }

}
