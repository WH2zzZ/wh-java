package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.LoadInstruction;

public final class ILOAD_3 extends Instruction implements LoadInstruction {

    public final int index = 3;

    public ILOAD_3() {
        super(OpcodeConst.ILOAD_3, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitILOAD_3(this);
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
