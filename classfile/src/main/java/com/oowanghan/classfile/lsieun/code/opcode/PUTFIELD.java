package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.FieldInstruction;
import com.oowanghan.classfile.lsieun.code.facet.PopInstruction;

public class PUTFIELD extends Instruction implements FieldInstruction, PopInstruction {

    public int index;

    public PUTFIELD() {
        super(OpcodeConst.PUTFIELD, 3);
    }

    public PUTFIELD(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitPUTFIELD(this);
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
