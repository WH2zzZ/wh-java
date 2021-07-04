package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;

public class WIDE extends Instruction {

    public WIDE() {
        super(OpcodeConst.WIDE, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitWIDE(this);
    }

}
