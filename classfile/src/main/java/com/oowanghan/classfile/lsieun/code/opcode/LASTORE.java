package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.ArrayInstruction;
import com.oowanghan.classfile.lsieun.code.facet.StackConsumer;

/**
 * LASTORE -  Store into long array
 * <PRE>Stack: ..., arrayref, index, value.word1, value.word2 -&gt; ...</PRE>
 */
public class LASTORE extends Instruction implements ArrayInstruction, StackConsumer {

    public LASTORE() {
        super(OpcodeConst.LASTORE, 1);
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitLASTORE(this);
    }

}
