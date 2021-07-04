package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

/**
 * INEG - Negate int
 * <PRE>Stack: ..., value -&gt; ..., result</PRE>
 */
public class INEG extends Instruction implements ArithmeticInstruction {

    public INEG() {
        super(OpcodeConst.INEG, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINEG(this);
    }

}
