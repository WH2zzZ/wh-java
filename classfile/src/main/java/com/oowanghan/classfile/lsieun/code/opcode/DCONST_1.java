package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class DCONST_1 extends Instruction implements ConstantPushInstruction {

    public double value = 1;

    public DCONST_1() {
        super(OpcodeConst.DCONST_1, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDCONST_1(this);
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }

}
