package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class SALOAD extends Instruction implements ArrayInstruction, StackProducer {

    public SALOAD() {
        super(OpcodeConst.SALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitSALOAD(this);
    }

}
