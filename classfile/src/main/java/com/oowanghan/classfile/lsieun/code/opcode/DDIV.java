package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

public class DDIV extends Instruction implements ArithmeticInstruction {

    public DDIV() {
        super(OpcodeConst.DDIV, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitDDIV(this);
    }

}
