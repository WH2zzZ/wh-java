package com.oowanghan.asm.classvisitor;

import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

/**
 * ClassVisitor 是一个抽象类, 要想使用它，就必须有具体的子类来继承它。
 * 比较常见的ClassVisitor子类有
 *      ClassWriter类（Core API）
 *      ClassNode类（Tree API）
 *
 * 1。介绍了ClassVisitor类的不同部分。我们去了解这个类不同的部分，是为了能够熟悉ClassVisitor这个类。
 * 2。在ClassVisitor类当中，定义了许多visitXxx()方法，这些方法的调用要遵循一定的顺序。
 * 3。在ClassVisitor类当中，定义的visitXxx()方法中的参数与ClassFile结构密切相关。
 *
 * @Author WangHan
 * @Create 2021/7/5 11:09 下午
 */
public class Demo01_ClassVisitor {

    /**
     * 子类：
     *  ClassWriter (core api)
     *  ClassNode (tree api)
     */
    @Test
    public void classInfo(){
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9){};
        ClassWriter classWriter = new ClassWriter(Opcodes.ASM9);
        ClassNode classNode = new ClassNode();
    }

    /**
     * 字段：
     * protected final int api;
     *     它是一个int类型的数据，指出了当前使用的ASM API版本，其取值有
     *     Opcodes.ASM4
     *     Opcodes.ASM5
     *     Opcodes.ASM6
     *     Opcodes.ASM7
     *     Opcodes.ASM8
     *     Opcodes.ASM9。
     *     我们使用的ASM版本是9.0，因此我们在给api字段赋值的时候，选择Opcodes.ASM9就可以了。
     * protected ClassVisitor cv;
     *     是一个ClassVisitor类型的数据，它的作用是将多个ClassVisitor串连起来
     *     每一个ClassVisitor只需要处理自己的那一份工作，类似责任链
     */
    @Test
    public void field(){
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9){};
    }

    /**
     * 构造方法
     */
    @Test
    public void constructors(){
        ClassVisitor classVisitor1 = new ClassVisitor(Opcodes.ASM9){};
        ClassVisitor classVisitor2 = new ClassVisitor(Opcodes.ASM9, classVisitor1){};
    }

    /**
     * 方法：在ASM当中，使用到了Visitor Pattern（访问者模式），所以ClassVisitor当中有很多的visitXxx()方法
     * 重点4个方法
     *      visit()
     *      visitField()
     *      visitMethod()
     *      visitEnd()
     *      方法中signature参数和“泛型”有关，如果处理的是带泛型的那么这个值才需要传值，否则为null
     *
     * 方法调用顺序
     *      1. visit
     *      2. 最多只能调用一次，可以不调用
     *         [visitSource][visitModule][visitNestHost][visitPermittedSubclass][visitOuterClass]
     *      3. 表示在多个方法之间，可以选择任意一个，并且多个方法之间不分前后顺序， * 表示方法可以调用0次或多次。
     *         (visitAnnotation | visitTypeAnnotation | visitAttribute)*
     *      4. (visitNestMember | visitInnerClass | visitRecordComponent | visitField | visitMethod)*
     *      5. visitEnd
     * 核心方法调用顺序
     *      visit
     *      (visitField | visitMethod)*
     *      visitEnd
     *      先调用visit()方法，接着调用visitField()方法或visitMethod()方法，最后调用visitEnd()方法。
     */

    /**
     * public void visit(
     *     final int version, = minor_version & major_version
     *     final int access,  = access_flags
     *     final String name, = this_class
     *     final String signature, = attributes部分信息
     *     final String superName, = super_class
     *     final String[] interfaces); = interfaces_count & interfaces[interfaces_count]
     *
     * ClassFile {
     *     u4             magic;
     *     u2             minor_version;
     *     u2             major_version;
     *     u2             constant_pool_count;
     *     cp_info        constant_pool[constant_pool_count-1];
     *     u2             access_flags;
     *     u2             this_class;
     *     u2             super_class;
     *     u2             interfaces_count;
     *     u2             interfaces[interfaces_count];
     *     u2             fields_count;
     *     field_info     fields[fields_count];
     *     u2             methods_count;
     *     method_info    methods[methods_count];
     *     u2             attributes_count;
     *     attribute_info attributes[attributes_count];
     * }
     *
     * public FieldVisitor visitField( // 访问字段
     *     final int access,           = access_flags
     *     final String name,          = name_index
     *     final String descriptor,    = descriptor_index
     *     final String signature,     = attributes
     *     final Object value);        = attributes
     *
     * field_info {
     *     u2             access_flags;
     *     u2             name_index;
     *     u2             descriptor_index;
     *     u2             attributes_count;
     *     attribute_info attributes[attributes_count];
     * }
     *
     * public MethodVisitor visitMethod( // 访问方法
     *     final int access,             = access_flags
     *     final String name,            = name_index
     *     final String descriptor,      = descriptor_index
     *     final String signature,       = attributes
     *     final String[] exceptions);   = attributes
     *
     * method_info {
     *     u2             access_flags;
     *     u2             name_index;
     *     u2             descriptor_index;
     *     u2             attributes_count;
     *     attribute_info attributes[attributes_count];
     * }
     */
    @Test
    public void methods(){

    }
}
