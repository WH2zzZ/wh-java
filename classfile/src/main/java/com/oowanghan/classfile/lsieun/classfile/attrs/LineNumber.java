package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public class LineNumber {
    public final int start_pc;
    public final int line_number;

    public LineNumber(ByteDashboard bd) {
        this.start_pc = bd.readUnsignedShort();
        this.line_number = bd.readUnsignedShort();
    }
}
