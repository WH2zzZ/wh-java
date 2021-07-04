package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConversionInstruction;

public class I2B extends Instruction implements ConversionInstruction {

    public I2B() {
        super(OpcodeConst.I2B, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitI2B(this);
    }

}
