package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class DCONST_0 extends Instruction implements ConstantPushInstruction {

    public double value = 0;

    public DCONST_0() {
        super(OpcodeConst.DCONST_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDCONST_0(this);
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }

}
