package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class MethodParameter {
    public final int name_index;
    public final int access_flags;

    public MethodParameter(ByteDashboard bd) {
        this.name_index = bd.readUnsignedShort();
        this.access_flags = bd.readUnsignedShort();
    }
}
