package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StoreInstruction;

public final class FSTORE_3 extends Instruction implements StoreInstruction {

    public final int index = 3;

    public FSTORE_3() {
        super(OpcodeConst.FSTORE_3, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFSTORE_3(this);
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
