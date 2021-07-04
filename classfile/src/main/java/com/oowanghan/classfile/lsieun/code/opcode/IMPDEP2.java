package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;

/**
 * IMPDEP2 - Implementation dependent
 */
public class IMPDEP2 extends Instruction {

    public IMPDEP2() {
        super(OpcodeConst.IMPDEP2, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitIMPDEP2(this);
    }

}
