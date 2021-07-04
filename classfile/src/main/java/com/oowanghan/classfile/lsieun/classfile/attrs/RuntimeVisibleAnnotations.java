package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.classfile.attrs.annotation.AnnotationEntry;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class RuntimeVisibleAnnotations extends AttributeInfo {
    public final int num_annotations;
    public final AnnotationEntry[] annotations;

    public RuntimeVisibleAnnotations(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);

        this.num_annotations = bd.readUnsignedShort();
        this.annotations = new AnnotationEntry[num_annotations];

        for (int i = 0; i < num_annotations; i++) {
            AnnotationEntry item = new AnnotationEntry(bd, cp);
            this.annotations[i] = item;
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitRuntimeVisibleAnnotations(this);
    }
}
