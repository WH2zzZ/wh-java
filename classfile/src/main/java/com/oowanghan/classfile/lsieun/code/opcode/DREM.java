package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

public class DREM extends Instruction implements ArithmeticInstruction {

    public DREM() {
        super(OpcodeConst.DREM, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDREM(this);
    }

}
