package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ReturnInstruction;

public class RETURN extends Instruction implements ReturnInstruction {

    public RETURN() {
        super(OpcodeConst.RETURN, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitRETURN(this);
    }

}
