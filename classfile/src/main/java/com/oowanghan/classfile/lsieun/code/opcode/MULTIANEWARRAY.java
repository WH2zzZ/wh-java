package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.CPInstruction;
import com.oowanghan.classfile.lsieun.code.facet.AllocationInstruction;
import com.oowanghan.classfile.lsieun.code.facet.LoadClass;

public class MULTIANEWARRAY extends Instruction
        implements CPInstruction, LoadClass, AllocationInstruction {

    public int index;
    public int dimensions;

    public MULTIANEWARRAY() {
        super(OpcodeConst.MULTIANEWARRAY, 4);
    }

    public MULTIANEWARRAY(final int index, final short dimensions) {
        this();

        if (dimensions < 1) {
            throw new RuntimeException("Invalid dimensions value: " + dimensions);
        }

        this.index = index;
        this.dimensions = dimensions;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitMULTIANEWARRAY(this);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
