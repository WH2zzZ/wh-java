package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.IfInstruction;

public class IF_ICMPLE extends Instruction implements IfInstruction {

    public int branch;

    public IF_ICMPLE() {
        super(OpcodeConst.IF_ICMPLE, 3);
    }

    public IF_ICMPLE(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIF_ICMPLE(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
