package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StoreInstruction;

public final class DSTORE_2 extends Instruction implements StoreInstruction {

    public final int index = 2;

    public DSTORE_2() {
        super(OpcodeConst.DSTORE_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDSTORE_2(this);
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
