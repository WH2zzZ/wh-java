package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public final class ICONST_4 extends Instruction implements ConstantPushInstruction {

    public final int value = 4;

    public ICONST_4() {
        super(OpcodeConst.ICONST_4, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitICONST_4(this);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

}
