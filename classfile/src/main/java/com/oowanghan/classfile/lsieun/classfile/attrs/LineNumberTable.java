package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.utils.ByteUtils;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class LineNumberTable extends AttributeInfo {
    public final int line_number_table_length;
    public final LineNumber[] line_number_table;

    public LineNumberTable(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);

        byte[] line_number_table_length_bytes = bd.nextN(2);
        this.line_number_table_length = ByteUtils.bytesToInt(line_number_table_length_bytes, 0);

        this.line_number_table = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            LineNumber item = new LineNumber(bd);
            this.line_number_table[i] = item;
        }
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitLineNumberTable(this);
    }
}
