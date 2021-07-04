package com.oowanghan.classfile.lsieun.classfile.cp;

import com.oowanghan.classfile.lsieun.cst.CPConst;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.utils.ByteUtils;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class ConstantLong extends Constant {
    public final Long longValue;

    ConstantLong(ByteDashboard bd) {
        super(CPConst.CONSTANT_Long);

        byte[] tag_bytes = bd.nextN(1);
        byte[] value_bytes = bd.nextN(8);

        this.longValue = ByteUtils.toLong(value_bytes);
        super.value = String.valueOf(this.longValue);
        super.bytes = ByteUtils.concatenate(tag_bytes, value_bytes);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantLong(this);
    }

}
