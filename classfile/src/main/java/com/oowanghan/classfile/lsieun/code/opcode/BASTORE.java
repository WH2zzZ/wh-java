package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;

public class BASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public BASTORE() {
        super(OpcodeConst.BASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitBASTORE(this);
    }
}
