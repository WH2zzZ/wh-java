package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.LoadInstruction;

public class LLOAD extends Instruction implements LoadInstruction {

    public int index;

    public LLOAD() {
        super(OpcodeConst.LLOAD, 2);
    }

    public LLOAD(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLLOAD(this);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
