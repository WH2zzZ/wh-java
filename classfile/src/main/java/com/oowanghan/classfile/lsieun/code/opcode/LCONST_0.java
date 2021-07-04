package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class LCONST_0 extends Instruction implements ConstantPushInstruction {

    public final long value = 0;

    public LCONST_0() {
        super(OpcodeConst.LCONST_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitLCONST_0(this);
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }

}
