package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class SuperTypeTarget {
    public final int supertype_index;

    public SuperTypeTarget(ByteDashboard bd) {
        this.supertype_index = bd.readUnsignedShort();
    }
}
