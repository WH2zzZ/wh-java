package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CPInstruction;
import com.oowanghan.classfile.lsieun.code.facet.LoadClass;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class CHECKCAST extends Instruction
        implements CPInstruction, LoadClass, StackProducer, StackConsumer {

    public int index;

    public CHECKCAST() {
        super(OpcodeConst.CHECKCAST, 3);
    }

    public CHECKCAST(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitCHECKCAST(this);
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
