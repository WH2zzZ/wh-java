package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.IfInstruction;

public class IFEQ extends Instruction implements IfInstruction {

    public int branch;

    public IFEQ() {
        super(OpcodeConst.IFEQ, 3);
    }

    public IFEQ(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFEQ(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
