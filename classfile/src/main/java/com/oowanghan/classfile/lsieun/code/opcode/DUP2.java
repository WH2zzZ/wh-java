package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StackInstruction;
import com.oowanghan.classfile.lsieun.code.facet.PushInstruction;

public class DUP2 extends Instruction implements StackInstruction, PushInstruction {

    public DUP2() {
        super(OpcodeConst.DUP2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDUP2(this);
    }

}
