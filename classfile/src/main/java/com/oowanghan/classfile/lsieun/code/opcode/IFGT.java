package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.IfInstruction;

public class IFGT extends Instruction implements IfInstruction {

    public int branch;

    public IFGT() {
        super(OpcodeConst.IFGT, 3);
    }

    public IFGT(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIFGT(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
