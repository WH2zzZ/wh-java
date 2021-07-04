package com.oowanghan.classfile.lsieun.classfile.attrs.annotation.type;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class Table {
    public final int start_pc;
    public final int length;
    public final int index;

    public Table(ByteDashboard bd) {
        this.start_pc = bd.readUnsignedShort();
        this.length = bd.readUnsignedShort();
        this.index = bd.readUnsignedShort();
    }
}
