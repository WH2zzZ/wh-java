package com.oowanghan.classfile.lsieun.classfile.cp;

import com.oowanghan.classfile.lsieun.cst.CPConst;
import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.utils.ByteUtils;
import com.oowanghan.classfile.lsieun.vs.Visitor;

public final class ConstantInteger extends Constant {
    public final Integer intValue;

    ConstantInteger(ByteDashboard bd) {
        super(CPConst.CONSTANT_Integer);

        byte[] tag_bytes = bd.nextN(1);
        byte[] value_bytes = bd.nextN(4);

        this.intValue = ByteUtils.toInt(value_bytes);
        super.value = String.valueOf(this.intValue);
        super.bytes = ByteUtils.concatenate(tag_bytes, value_bytes);
    }

    @Override
    public void accept(Visitor obj) {
        obj.visitConstantInteger(this);
    }

}
