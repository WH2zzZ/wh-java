package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.GotoInstruction;

public class GOTO extends Instruction implements GotoInstruction {

    public int branch;

    public GOTO() {
        super(OpcodeConst.GOTO, 3);
    }

    public GOTO(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitGOTO(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }
}
