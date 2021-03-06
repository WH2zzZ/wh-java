package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class TypeArgumentTarget {
    public final int offset;
    public final int type_argument_index;

    public TypeArgumentTarget(ByteDashboard bd) {
        this.offset = bd.readUnsignedShort();
        this.type_argument_index = bd.readUnsignedByte();
    }
}
