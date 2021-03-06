package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class LCONST_1 extends Instruction implements ConstantPushInstruction {

    public final long value = 1;

    public LCONST_1() {
        super(OpcodeConst.LCONST_1, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitLCONST_1(this);
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }

}