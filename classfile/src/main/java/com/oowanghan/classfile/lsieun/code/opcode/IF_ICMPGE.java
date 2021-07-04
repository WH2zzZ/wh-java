package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.IfInstruction;

public class IF_ICMPGE extends Instruction implements IfInstruction {

    public int branch;

    public IF_ICMPGE() {
        super(OpcodeConst.IF_ICMPGE, 3);
    }

    public IF_ICMPGE(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIF_ICMPGE(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
