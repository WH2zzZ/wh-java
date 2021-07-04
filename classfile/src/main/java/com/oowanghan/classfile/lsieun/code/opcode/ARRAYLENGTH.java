package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class ARRAYLENGTH extends Instruction implements StackProducer, StackConsumer {

    public ARRAYLENGTH() {
        super(OpcodeConst.ARRAYLENGTH, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitARRAYLENGTH(this);
    }
}
