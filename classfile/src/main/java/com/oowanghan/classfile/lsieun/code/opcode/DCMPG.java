package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CompareInstruction;

public class DCMPG extends Instruction implements CompareInstruction {

    public DCMPG() {
        super(OpcodeConst.DCMPG, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDCMPG(this);
    }

}
