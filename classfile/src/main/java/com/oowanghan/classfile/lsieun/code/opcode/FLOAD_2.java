package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.LoadInstruction;

public final class FLOAD_2 extends Instruction implements LoadInstruction {

    public final int index = 2;

    public FLOAD_2() {
        super(OpcodeConst.FLOAD_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFLOAD_2(this);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        throw new RuntimeException("index is final");
    }

}
