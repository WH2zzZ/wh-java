package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.InvokeInstruction;

/**
 * INVOKESTATIC - Invoke a class (static) d_method
 *
 * <PRE>Stack: ..., [arg1, [arg2 ...]] -&gt; ...</PRE>
 */
public class INVOKESTATIC extends Instruction implements InvokeInstruction {

    public int index;

    public INVOKESTATIC() {
        super(OpcodeConst.INVOKESTATIC, 3);
    }

    public INVOKESTATIC(final int index) {
        this();
        this.index = index;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitINVOKESTATIC(this);
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
