package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.AllocationInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

public class NEWARRAY extends Instruction
        implements AllocationInstruction, StackProducer {

    public byte atype;

    public NEWARRAY() {
        super(OpcodeConst.NEWARRAY, 2);
    }

    public NEWARRAY(final byte atype) {
        this();
        this.atype = atype;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitNEWARRAY(this);
    }

}
