package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.cst.CPConst;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class SourceFile extends AttributeInfo {
    public final int sourcefile_index;

    public SourceFile(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);
        this.sourcefile_index = bd.readUnsignedShort();

        String value = cp.getConstantString(sourcefile_index, CPConst.CONSTANT_Utf8);
        super.value = value;
    }

    @Override
    public void accept(Visitor v) {
        v.visitSourceFile(this);
    }

}
