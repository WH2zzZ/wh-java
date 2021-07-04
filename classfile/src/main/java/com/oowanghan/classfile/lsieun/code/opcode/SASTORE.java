package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;

public class SASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public SASTORE() {
        super(OpcodeConst.SASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitSASTORE(this);
    }

}