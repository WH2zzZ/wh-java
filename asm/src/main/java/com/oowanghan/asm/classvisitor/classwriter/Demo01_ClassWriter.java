package com.oowanghan.asm.classvisitor.classwriter;

import com.oowanghan.asm.utils.FileUtils;
import com.oowanghan.asm.utils.HexUtils;
import org.junit.Test;
import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 *
 * @Author WangHan
 * @Create 2021/8/10 12:36 上午
 */
public class Demo01_ClassWriter {

    public static final String CLASS_INTERNAL_NAME = "com/oowanghan/asm/classvisitor/classwriter/generate/HelloWorld";

    @Test
    public void init() throws IOException {

        /*
        第一个，可以选取的值是0。ASM不会自动计算max stacks和max locals，也不会自动计算stack map frames。
        第二个，可以选取的值是ClassWriter.COMPUTE_MAXS。
            ASM会自动计算max stacks和max locals，但不会自动计算stack map frames。
        第三个，可以选取的值是ClassWriter.COMPUTE_FRAMES（推荐使用）。
            ASM会自动计算max stacks和max locals，也会自动计算stack map frames。
         */
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        // 这种会造成字节码更改时候造成的冗余
        ClassReader classReader = new ClassReader("xxx");
        ClassWriter cw1 = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
    }


    /**
     * version: 表示当前类的版本信息。在上述示例代码中，其取值为Opcodes.V1_8，表示使用Java 8版本。
     * access: 表示当前类的访问标识（access flag）信息。在上面的示例中，access的取值是ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE，
     *      也可以写成ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE。如果想进一步了解这些标识的含义，
     *      可以参考 Java Virtual Machine Specification的Chapter 4. The class File Format部分。
     * name: 表示当前类的名字，它采用的格式是Internal Name的形式。
     * signature: 表示当前类的泛型信息。因为在这个接口当中不包含任何的泛型信息，因此它的值为null。
     * superName: 表示当前类的父类信息，它采用的格式是Internal Name的形式。
     * interfaces: 表示当前类实现了哪些接口信息。
     *
     * Internal Name的概念：
     * 1. 在.java文件中，我们使用Java语言来编写代码，使用类名的形式是Fully Qualified Class Name，
     *      例如java.lang.String；将.java文件编译之后，就会生成.class文件；
     * 2. 在.class文件中，类名的形式会发生变化，称之为Internal Name，
     *      例如java/lang/String。
     * 3. 因此，将Fully Qualified Class Name转换成Internal Name的方式就是，将.字符转换成/字符
     */
    @Test
    public void generateHelloWorld_Interface () {
        // (1) 创建ClassWriter对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        // (2) 调用visitXxx()方法
        cw.visit(
                // 1.8 version
                V1_8,
                // 是public级别
                // access的取值是ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE，也可以写成ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE
//                Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
                ACC_PUBLIC | Opcodes.ACC_INTERFACE,
                // 类的全限定名
                CLASS_INTERNAL_NAME,
                null,
                // 父类
                "java/lang/Object",
                // 是否有实现的接口
                null);

        cw.visitEnd();

        // (3) 调用toByteArray()方法
        byte[] bytes = cw.toByteArray();

        System.out.println(HexUtils.toHex(bytes));
        String filePath = FileUtils.getFilePath("com/oowanghan/asm/classvisitor/classwriter/generate/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);
    }


    /**
     * access参数：表示当前字段或方法带有的访问标识（access flag）信息，例如ACC_PUBLIC、ACC_STATIC和ACC_FINAL等。
     * name参数：表示当前字段或方法的名字。
     * descriptor参数：表示当前字段或方法的描述符。这些描述符，与我们平时使用的Java类型是有区别的。
     * signature参数：表示当前字段或方法是否带有泛型信息。换句话说，如果不带有泛型信息，提供一个null就可以了；如果带有泛型信息，就需要给它提供某一个具体的值。
     * value参数：是visitField()方法的第5个参数。
     *      这个参数的取值，与当前字段是否为常量有关系。
     *      如果当前字段是一个常量，就需要给value参数提供某一个具体的值；
     *      如果当前字段不是常量，那么使用null就可以了。
     * exceptions参数：是visitMethod()方法的第5个参数。这个参数的取值，与当前方法声明中是否具有throws XxxException相关。
     */
    /**
     * FieldType term	Type	        Interpretation
     * B	            byte	        signed byte
     * C	            char	        Unicode character code point in the Basic Multilingual Plane, encoded with UTF-16
     * D	            double	        double-precision floating-point value
     * F	            float	        single-precision floating-point value
     * I	            int	            integer
     * J	            long	        long integer
     * L ClassName ;	reference	    an instance of class ClassName
     * S	            short	        signed short
     * Z	            boolean	        true or false
     * [	            reference	    one array dimension
     *
     * boolean      : Z
     * byte         : B
     * int          : I
     * float        : F
     * double       : D
     * String       : Ljava/lang/String;
     * Object       : Ljava/lang/Object;
     * byte[]       : [B
     * String[]     : [Ljava/lang/String;
     * Object[][]   : [[Ljava/lang/Object;
     */
    @Test
    public void generateHelloWorld_Interface_Field () {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        classWriter.visit(
                V1_8,
                ACC_PUBLIC | Opcodes.ACC_INTERFACE,
                CLASS_INTERNAL_NAME,
                null,
                "java/lang/Object",
                new String[]{"java/lang/Cloneable"});

        FieldVisitor fv1 = classWriter.visitField(
                ACC_PUBLIC | ACC_FINAL | ACC_STATIC,
                "PARAM1",
                "Ljava.lang.Integer",
                null,
                null
        );
        fv1.visitEnd();

        FieldVisitor fv2 = classWriter.visitField(
                ACC_PUBLIC | ACC_FINAL | ACC_STATIC,
                "PARAM2",
                "I",
                null,
                2
        );
        fv2.visitEnd();

        FieldVisitor fv3 = classWriter.visitField(
                ACC_PUBLIC | ACC_FINAL | ACC_STATIC,
                "PARAM3",
                "I",
                null,
                3
        );
        fv3.visitEnd();

        classWriter.visitEnd();
        byte[] bytes = classWriter.toByteArray();

        System.out.println(HexUtils.toHex(bytes));
        String filePath = FileUtils.getFilePath("com/oowanghan/asm/classvisitor/classwriter/generate/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);
    }

    /**
     * 对于一个类（Class）来说，如果没有提供任何构造方法，Java编译器会自动生成一个默认构造方法。在所有的.class文件中，构造方法的名字是<init>()。
     *
     * 另外，如果在.class文件中包含静态代码块，那么就会有一个<clinit>()方法。
     *
     * public class HelloWorld {
     *     static {
     *         System.out.println("static code block");
     *     }
     * }
     * 上面的静态代码码，对应于visitMethod(ACC_STATIC, "<clinit>", "()V", null, null)的调用。
     */
    @Test
    public void generateHelloWorld_Class(){
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(
                V1_8,
                ACC_PUBLIC | ACC_SUPER,
                CLASS_INTERNAL_NAME,
                null,
                "java/lang/Object",
                null
        );

        // <init>表示参构造方法名字
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        MethodVisitor mv1 = cw.visitMethod(
                ACC_PUBLIC + ACC_ABSTRACT,
                "compareTo",
                // 如果是多个参数直接写在后面就行了，比如 BI 就代表两个入参，byte和int
                // ()表示入参，后面更的就是返回的
                "(Ljava/lang/Object;BI)I",
                null, null);
        mv1.visitEnd();

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        System.out.println(HexUtils.toHex(bytes));
        String filePath = FileUtils.getFilePath("com/oowanghan/asm/classvisitor/classwriter/generate/HelloWorld.class");
        FileUtils.writeBytes(filePath, bytes);

    }
}
