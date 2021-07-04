package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConversionInstruction;

public class D2F extends Instruction implements ConversionInstruction {

    public D2F() {
        super(OpcodeConst.D2F, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitD2F(this);
    }

}
