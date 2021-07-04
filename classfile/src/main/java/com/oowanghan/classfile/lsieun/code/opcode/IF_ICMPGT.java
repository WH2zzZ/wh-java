package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.IfInstruction;

public class IF_ICMPGT extends Instruction implements IfInstruction {

    public int branch;

    public IF_ICMPGT() {
        super(OpcodeConst.IF_ICMPGT, 3);
    }

    public IF_ICMPGT(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIF_ICMPGT(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
