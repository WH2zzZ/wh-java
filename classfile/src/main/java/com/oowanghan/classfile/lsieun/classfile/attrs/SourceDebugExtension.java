package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public class SourceDebugExtension extends AttributeInfo {
    public final byte[] debug_extension;

    public SourceDebugExtension(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);
        this.debug_extension = bd.nextN(attribute_length);
    }

    @Override
    public void accept(Visitor v) {
        v.visitSourceDebugExtension(this);
    }
}
