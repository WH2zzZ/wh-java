package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CPInstruction;
import com.oowanghan.classfile.lsieun.code.facet.AllocationInstruction;
import com.oowanghan.classfile.lsieun.code.facet.LoadClass;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class ANEWARRAY extends Instruction
        implements CPInstruction, LoadClass, AllocationInstruction, StackConsumer, StackProducer {

    public int index;

    public ANEWARRAY() {
        super(OpcodeConst.ANEWARRAY, 3);
    }

    public ANEWARRAY(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitANEWARRAY(this);
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
