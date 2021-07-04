package com.oowanghan.classfile.lsieun.code.type;

import com.oowanghan.classfile.lsieun.cst.TypeConst;

/**
 * Super class for object and array types.
 */
public abstract class ReferenceType extends Type {
    protected ReferenceType(final byte t, final String s) {
        super(t, s);
    }


    /** Class is non-abstract but not instantiable from the outside
     */
    public ReferenceType() {
        super(TypeConst.T_OBJECT, "null");
    }

}
