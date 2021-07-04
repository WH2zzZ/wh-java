package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class Deprecated extends AttributeInfo {
    public Deprecated(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);
    }

    @Override
    public void accept(Visitor v) {
        v.visitDeprecated(this);
    }
}
