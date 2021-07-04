package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.StackInstruction;

public class DUP2_X2 extends Instruction implements StackInstruction {

    public DUP2_X2() {
        super(OpcodeConst.DUP2_X2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitDUP2_X2(this);
    }

}
