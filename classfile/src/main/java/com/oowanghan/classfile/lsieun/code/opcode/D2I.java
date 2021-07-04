package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConversionInstruction;


public class D2I extends Instruction implements ConversionInstruction {

    public D2I() {
        super(OpcodeConst.D2I, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitD2I(this);
    }

}
