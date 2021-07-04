package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.InvokeInstruction;

/**
 * INVOKESPECIAL - Invoke instance d_method; special handling for superclass, private
 * and instance initialization d_method invocations
 *
 * <PRE>Stack: ..., objectref, [arg1, [arg2 ...]] -&gt; ...</PRE>
 */
public class INVOKESPECIAL extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKESPECIAL() {
        super(OpcodeConst.INVOKESPECIAL, 3);
    }

    public INVOKESPECIAL(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKESPECIAL(this);
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
