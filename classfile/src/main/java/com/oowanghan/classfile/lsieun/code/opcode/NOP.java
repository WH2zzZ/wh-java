package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;

/**
 * NOP - Do nothing
 */
public class NOP extends Instruction {

    public NOP() {
        super(OpcodeConst.NOP, 1);
    }

    @Override
    public void accept(OpcodeVisitor v) {
        v.visitNOP(this);
    }

}
