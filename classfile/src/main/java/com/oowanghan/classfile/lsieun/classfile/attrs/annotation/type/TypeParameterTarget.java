package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class TypeParameterTarget {
    public final int type_parameter_index;

    public TypeParameterTarget(ByteDashboard bd) {
        this.type_parameter_index = bd.readUnsignedByte();
    }
}
