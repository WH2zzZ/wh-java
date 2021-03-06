package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class LocalVariableTable extends AttributeInfo {
    public final int local_variable_table_length;
    public final LocalVariable[] entries;

    public LocalVariableTable(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);

        this.local_variable_table_length = bd.readUnsignedShort();
        this.entries = new LocalVariable[local_variable_table_length];

        for(int i = 0; i< local_variable_table_length; i++) {
            LocalVariable item = new LocalVariable(bd, cp);
            this.entries[i] = item;
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitLocalVariableTable(this);
    }
}
