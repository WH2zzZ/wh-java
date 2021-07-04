package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.InvokeInstruction;

/**
 * INVOKEVIRTUAL - Invoke instance d_method; dispatch based on class
 *
 * <PRE>Stack: ..., objectref, [arg1, [arg2 ...]] -&gt; ...</PRE>
 */
public class INVOKEVIRTUAL extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKEVIRTUAL() {
        super(OpcodeConst.INVOKEVIRTUAL, 3);
    }

    public INVOKEVIRTUAL(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKEVIRTUAL(this);
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
