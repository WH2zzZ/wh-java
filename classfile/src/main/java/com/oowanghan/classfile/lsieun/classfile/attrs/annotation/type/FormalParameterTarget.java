package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class FormalParameterTarget {
    public final int formal_parameter_index;

    public FormalParameterTarget(ByteDashboard bd) {
        this.formal_parameter_index = bd.readUnsignedByte();
    }
}
