package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class FCONST_2 extends Instruction implements ConstantPushInstruction {

    public final float value = 2;

    public FCONST_2() {
        super(OpcodeConst.FCONST_2, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitFCONST_2(this);
    }

    @Override
    public Number getValue() {
        return Float.valueOf(value);
    }

}
