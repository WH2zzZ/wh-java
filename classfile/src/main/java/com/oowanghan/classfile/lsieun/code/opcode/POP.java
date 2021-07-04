package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.PopInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackInstruction;

/**
 * POP - Pop top operand f_stack word
 *
 * <PRE>Stack: ..., word -&gt; ...</PRE>
 */
public class POP extends Instruction implements StackInstruction, PopInstruction {

    public POP() {
        super(OpcodeConst.POP, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPOP(this);
    }

}
