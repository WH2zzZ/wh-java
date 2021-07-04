package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StackInstruction;
import com.oowanghan.classfile.lsieun.code.facet.PushInstruction;

public class DUP extends Instruction implements StackInstruction, PushInstruction {

    public DUP() {
        super(OpcodeConst.DUP, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDUP(this);
    }

}
