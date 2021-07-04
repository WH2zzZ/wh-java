package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.classfile.attrs.annotation.ElementValue;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public class AnnotationDefault extends AttributeInfo {
    public final ElementValue default_value;

    public AnnotationDefault(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);
        this.default_value = ElementValue.readElementValue(bd, cp);
    }

    @Override
    public void accept(Visitor v) {
        v.visitAnnotationDefault(this);
    }
}
