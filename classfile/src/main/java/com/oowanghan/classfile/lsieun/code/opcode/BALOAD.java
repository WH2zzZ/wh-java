package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class BALOAD extends Instruction implements ArrayInstruction, StackProducer {

    public BALOAD() {
        super(OpcodeConst.BALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitBALOAD(this);
    }
}
