package com.oowanghan.classfile.lsieun.code.opcode;

import com.oowanghan.classfile.lsieun.cst.OpcodeConst;
import com.oowanghan.classfile.lsieun.code.Instruction;
import com.oowanghan.classfile.lsieun.code.visitors.OpcodeVisitor;
import com.oowanghan.classfile.lsieun.code.facet.JsrInstruction;
import com.oowanghan.classfile.lsieun.code.facet.VariableLengthInstruction;

/**
 * JSR - Jump to subroutine
 */
public class JSR extends Instruction implements JsrInstruction, VariableLengthInstruction {

    public int branch;

    public JSR() {
        super(OpcodeConst.JSR, 3);
    }

    public JSR(final int branch) {
        this();
        this.branch = branch;
    }

    @Override
    public void accept(final OpcodeVisitor v) {
        v.visitJSR(this);
    }

    @Override
    public int getDefaultBranch() {
        return branch;
    }

}
