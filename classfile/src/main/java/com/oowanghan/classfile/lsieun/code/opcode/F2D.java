package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConversionInstruction;

public class F2D extends Instruction implements ConversionInstruction {

    public F2D() {
        super(OpcodeConst.F2D, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitF2D(this);
    }

}
