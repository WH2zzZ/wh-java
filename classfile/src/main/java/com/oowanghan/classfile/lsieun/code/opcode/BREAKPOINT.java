package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;

/**
 * BREAKPOINT, JVM dependent, ignored by default
 */
public class BREAKPOINT extends Instruction {

    public BREAKPOINT() {
        super(OpcodeConst.BREAKPOINT, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitBREAKPOINT(this);
    }
}
