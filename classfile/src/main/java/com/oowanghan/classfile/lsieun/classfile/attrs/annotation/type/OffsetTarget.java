package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class OffsetTarget {
    public final int offset;

    public OffsetTarget(ByteDashboard bd) {
        this.offset = bd.readUnsignedShort();
    }
}
