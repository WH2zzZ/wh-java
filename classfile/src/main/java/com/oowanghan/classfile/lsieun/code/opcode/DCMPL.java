package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CompareInstruction;

public class DCMPL extends Instruction implements CompareInstruction {

    public DCMPL() {
        super(OpcodeConst.DCMPL, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDCMPL(this);
    }

}
