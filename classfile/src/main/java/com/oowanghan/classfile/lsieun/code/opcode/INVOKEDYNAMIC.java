package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.InvokeInstruction;

/**
 * Class for INVOKEDYNAMIC. Not an instance of InvokeInstruction, since that class
 * expects to be able to get the class of the d_method. Ignores the bootstrap
 * mechanism entirely.
 */
public class INVOKEDYNAMIC extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKEDYNAMIC() {
        super(OpcodeConst.INVOKEDYNAMIC, 5);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKEDYNAMIC(this);
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
