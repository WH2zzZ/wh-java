package com.oowanghan.classfile.lsieun.classfile.attrs;

import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class LocalVariableTypeTable extends AttributeInfo {
    public final int local_variable_type_table_length;
    public final LocalVariableType[] entries;

    public LocalVariableTypeTable(ByteDashboard bd, ConstantPool cp) {
        super(bd, cp);

        this.local_variable_type_table_length = bd.readUnsignedShort();
        this.entries = new LocalVariableType[local_variable_type_table_length];

        for (int i = 0; i < local_variable_type_table_length; i++) {
            LocalVariableType item = new LocalVariableType(bd, cp);
            this.entries[i] = item;
        }
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitLocalVariableTypeTable(this);
    }
}
