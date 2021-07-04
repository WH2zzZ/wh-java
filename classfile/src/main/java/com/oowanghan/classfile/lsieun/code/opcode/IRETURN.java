package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ReturnInstruction;

/**
 * IRETURN -  Return int from d_method
 * <PRE>Stack: ..., value -&gt; &lt;empty&gt;</PRE>
 */
public class IRETURN extends Instruction implements ReturnInstruction {

    public IRETURN() {
        super(OpcodeConst.IRETURN, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIRETURN(this);
    }

}
