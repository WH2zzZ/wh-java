package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.TypedInstruction;

public class ACONST_NULL extends Instruction implements TypedInstruction {

    public ACONST_NULL() {
        super(OpcodeConst.ACONST_NULL, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitACONST_NULL(this);
    }

}
