package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class ICONST_0 extends Instruction implements ConstantPushInstruction {

    public final int value = 0;

    public ICONST_0() {
        super(OpcodeConst.ICONST_0, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitICONST_0(this);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

}
