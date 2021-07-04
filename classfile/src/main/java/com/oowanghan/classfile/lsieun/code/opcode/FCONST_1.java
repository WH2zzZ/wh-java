package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class FCONST_1 extends Instruction implements ConstantPushInstruction {

    public final float value = 1;

    public FCONST_1() {
        super(OpcodeConst.FCONST_1, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFCONST_1(this);
    }

    @Override
    public Number getValue() {
        return Float.valueOf(value);
    }

}
