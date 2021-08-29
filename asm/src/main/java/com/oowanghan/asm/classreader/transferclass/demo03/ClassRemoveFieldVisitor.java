package com.oowanghan.asm.classreader.transferclass.demo03;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class ClassRemoveFieldVisitor extends ClassVisitor {
    private final String fieldName;
    private final String fieldDesc;

    public ClassRemoveFieldVisitor(int api, ClassVisitor cv, String fieldName, String fieldDesc) {
        super(api, cv);
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    /**
     * ClassVisitor.visitField()方法。在正常的情况下，ClassVisitor.visitField()方法返回一个FieldVisitor对象；
     * 但是，如果ClassVisitor.visitField()方法返回的是null，就么能够达到删除该字段的效果。
     * @param access
     * @param name
     * @param descriptor
     * @param signature
     * @param value
     * @return
     */
    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (name.equals(fieldName) && descriptor.equals(fieldDesc)) {
            return null;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }
}