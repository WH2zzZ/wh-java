package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CompareInstruction;

public class LCMP extends Instruction implements CompareInstruction {

    public LCMP() {
        super(OpcodeConst.LCMP, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLCMP(this);
    }

}
