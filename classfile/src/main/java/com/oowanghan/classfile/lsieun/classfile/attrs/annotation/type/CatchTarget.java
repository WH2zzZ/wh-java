package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class CatchTarget {
    public final int exception_table_index;

    public CatchTarget(ByteDashboard bd) {
        this.exception_table_index = bd.readUnsignedShort();
    }
}
