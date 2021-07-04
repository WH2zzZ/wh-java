package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.FieldInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class GETFIELD extends Instruction implements FieldInstruction, StackConsumer, StackProducer {

    public int index;

    public GETFIELD() {
        super(OpcodeConst.GETFIELD, 3);
    }

    public GETFIELD(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitGETFIELD(this);
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
