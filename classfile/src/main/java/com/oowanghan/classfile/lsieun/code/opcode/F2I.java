package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConversionInstruction;

public class F2I extends Instruction implements ConversionInstruction {

    public F2I() {
        super(OpcodeConst.F2I, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitF2I(this);
    }

}
