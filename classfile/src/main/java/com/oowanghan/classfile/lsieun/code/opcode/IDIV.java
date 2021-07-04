package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

public class IDIV extends Instruction implements ArithmeticInstruction {

    public IDIV() {
        super(OpcodeConst.IDIV, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIDIV(this);
    }

}
