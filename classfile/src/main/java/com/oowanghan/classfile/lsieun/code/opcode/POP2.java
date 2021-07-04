package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.PopInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackInstruction;

public class POP2 extends Instruction implements StackInstruction, PopInstruction {

    public POP2() {
        super(OpcodeConst.POP2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPOP2(this);
    }

}
