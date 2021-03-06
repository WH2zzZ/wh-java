package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.cst.CPConst;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class Signature extends AttributeInfo {
    public final int signature_index;

    public Signature(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);

        this.signature_index = bd.readUnsignedShort();
        this.value = cp.getConstantString(signature_index, CPConst.CONSTANT_Utf8);
    }

    @Override
    public void accept(Visitor v) {
        v.visitSignature(this);
    }
}
