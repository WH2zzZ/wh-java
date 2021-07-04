package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CPInstruction;
import com.oowanghan.classfile.lsieun.code.facet.AllocationInstruction;
import com.oowanghan.classfile.lsieun.code.facet.LoadClass;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class NEW extends Instruction
        implements CPInstruction, LoadClass, AllocationInstruction, StackProducer {

    public int index;

    public NEW() {
        super(OpcodeConst.NEW, 3);
    }


    public NEW(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitNEW(this);
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
