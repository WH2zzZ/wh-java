package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackProducer;

/**
 * LALOAD - Load long from array
 * <PRE>Stack: ..., arrayref, index -&gt; ..., value1, value2</PRE>
 */
public class LALOAD extends Instruction implements ArrayInstruction, StackProducer {

    public LALOAD() {
        super(OpcodeConst.LALOAD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLALOAD(this);
    }

}
