package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.IfInstruction;

public class IFNONNULL extends Instruction implements IfInstruction {

    public int branch;

    public IFNONNULL() {
        super(OpcodeConst.IFNONNULL, 3);
    }

    public IFNONNULL(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFNONNULL(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
