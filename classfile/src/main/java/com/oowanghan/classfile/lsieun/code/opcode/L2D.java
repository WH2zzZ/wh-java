package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ConversionInstruction;

/**
 * L2D - Convert long to double
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ..., result.word1, result.word2</PRE>
 */
public class L2D extends Instruction implements ConversionInstruction {

    public L2D() {
        super(OpcodeConst.L2D, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitL2D(this);
    }

}
