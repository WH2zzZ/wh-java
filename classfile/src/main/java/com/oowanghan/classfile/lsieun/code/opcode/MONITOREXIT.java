package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;

public class MONITOREXIT extends Instruction implements StackConsumer {

    public MONITOREXIT() {
        super(OpcodeConst.MONITOREXIT, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitMONITOREXIT(this);
    }

}
