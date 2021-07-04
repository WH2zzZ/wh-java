package com.oowanghan.classfile.lsieun.classfile.attrs.annotation;


import com.oowanghan.classfile.lsieun.classfile.ConstantPool;
import com.oowanghan.classfile.lsieun.cst.CPConst;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;

public final class ClassElementValue extends ElementValue {
    public final int class_info_index;

    public ClassElementValue(ByteDashboard bd, final ConstantPool cp) {
        super(bd);

        this.class_info_index = bd.readUnsignedShort();

        this.value = cp.getConstantString(class_info_index, CPConst.CONSTANT_Utf8);
    }

    @Override
    public String stringifyValue() {
        return this.value;
    }
}
