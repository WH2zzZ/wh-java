package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.UnconditionalBranch;

public class ATHROW extends Instruction implements UnconditionalBranch {

    public ATHROW() {
        super(OpcodeConst.ATHROW, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitATHROW(this);
    }
}
