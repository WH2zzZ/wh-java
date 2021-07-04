package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class IALOAD extends Instruction implements ArrayInstruction, StackProducer {

    public IALOAD() {
        super(OpcodeConst.IALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIALOAD(this);
    }

}
