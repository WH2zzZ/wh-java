package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

/**
 * IOR - Bitwise OR int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public class IOR extends Instruction implements ArithmeticInstruction {

    public IOR() {
        super(OpcodeConst.IOR, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIOR(this);
    }

}
