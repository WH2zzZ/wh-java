package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArithmeticInstruction;

/**
 * LADD - Add longs
 * <PRE>Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt;</PRE>
 * ..., result.word1, result.word2
 */
public class LADD extends Instruction implements ArithmeticInstruction {

    public LADD() {
        super(OpcodeConst.LADD, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLADD(this);
    }

}
