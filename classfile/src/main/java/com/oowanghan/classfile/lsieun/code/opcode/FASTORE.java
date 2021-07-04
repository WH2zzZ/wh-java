package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;

public class FASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public FASTORE() {
        super(OpcodeConst.FASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitFASTORE(this);
    }

}
