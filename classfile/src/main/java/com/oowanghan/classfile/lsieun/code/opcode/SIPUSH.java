package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConstantPushInstruction;

public class SIPUSH extends Instruction implements ConstantPushInstruction {

    public short value;

    public SIPUSH() {
        super(OpcodeConst.SIPUSH, 3);
    }

    public SIPUSH(final short value) {
        this();
        this.value = value;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitSIPUSH(this);
    }

}
