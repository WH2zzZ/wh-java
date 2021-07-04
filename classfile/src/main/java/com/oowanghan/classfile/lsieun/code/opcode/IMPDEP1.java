package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;

/**
 * IMPDEP1 - Implementation dependent
 */
public class IMPDEP1 extends Instruction {

    public IMPDEP1() {
        super(OpcodeConst.IMPDEP1, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIMPDEP1(this);
    }

}
