package com.oowanghan.classfile.lsieun.classfile.cp;

import com.oowanghan.classfile.lsieun.cst.CPConst;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.utils.ByteUtils;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class ConstantDouble extends Constant {
    public final Double doubleValue;

    public ConstantDouble(ByteDashboard bd) {
        super(CPConst.CONSTANT_Double);

        byte[] tag_bytes = bd.nextN(1);
        byte[] value_bytes = bd.nextN(8);

        this.doubleValue = ByteUtils.toDouble(value_bytes);
        super.value = String.valueOf(this.doubleValue);
        super.bytes = ByteUtils.concatenate(tag_bytes, value_bytes);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantDouble(this);
    }

}
