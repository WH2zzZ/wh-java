package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.classfile.attrs.annotation.ParameterAnnotation;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public class RuntimeVisibleParameterAnnotations extends AttributeInfo {
    public final int num_parameters;
    public final ParameterAnnotation[] parameter_annotations;

    public RuntimeVisibleParameterAnnotations(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);

        this.num_parameters = bd.readUnsignedByte();
        this.parameter_annotations = new ParameterAnnotation[num_parameters];
        for (int i = 0; i < num_parameters; i++) {
            parameter_annotations[i] = new ParameterAnnotation(bd, cp);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitRuntimeVisibleParameterAnnotations(this);
    }
}
