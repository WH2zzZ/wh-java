package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;

public class CASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public CASTORE() {
        super(OpcodeConst.CASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitCASTORE(this);
    }

}
