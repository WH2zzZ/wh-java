package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;

public class AALOAD extends Instruction implements ArrayInstruction {

    public AALOAD() {
        super(OpcodeConst.AALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitAALOAD(this);
    }
}
