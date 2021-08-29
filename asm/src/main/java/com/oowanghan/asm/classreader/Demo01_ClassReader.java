package com.oowanghan.asm.classreader;

import com.oowanghan.asm.utils.FileUtils;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 字段介绍：
 * public class ClassReader {
 * //第1组，真实的数据部分
 * final byte[] classFileBuffer;
 *
 * //第2组，数据的索引信息
 * private final int[] cpInfoOffsets;
 * public final int header;
 * }
 *
 * classFileBuffer字段：包含的信息就是从.class文件中读取出来的字节码数据。
 * cpInfoOffsets字段：标识了classFileBuffer中数据里包含的常量池（constant pool）
 * header字段: 访问标识（access flag）的位置信息
 *
 * 使用思路：
 * .class文件 --> ClassReader --> byte[] --> 经过各种转换 --> ClassWriter --> byte[] --> .class文件
 * 第一，从一个.class文件（例如HelloWorld.class）开始，它可能存储于磁盘的某个位置；
 * 第二，使用ClassReader类将这个.class文件的内容读取出来，其实这些内容（byte[]）就是ClassReader对象中的classFileBuffer字段的内容；
 * 第三，为了增加某些功能，就对这些原始内容（byte[]）进行转换；
 * 第四，等各种转换都完成之后，再交给ClassWriter类处理，调用它的toByteArray()方法，从而得到新的内容（byte[]）；
 * 第五，将新生成的内容（byte[]）存储到一个具体的.class文件中，那么这个新的.class文件就具备了一些新的功能。
 *
 * ClassReader --> ClassVisitor(1) --> ... --> ClassVisitor(N) --> ClassWriter
 * ClassReader类，是ASM提供的一个类，可以直接拿来使用。
 * ClassWriter类，是ASM提供的一个类，可以直接拿来使用。
 * ClassVisitor类，是ASM提供的一个抽象类，因此需要写代码提供一个ClassVisitor的子类，在这个子类当中可以实现对.class文件进行各种处理操作。
 * 换句话说，进行Class Transformation操作，编写ClassVisitor的子类是关键。
 * @Author WangHan
 * @Create 2021/8/23 1:38 上午
 */
public class Demo01_ClassReader {

    @Test
    public void constructors() throws IOException {
        ClassReader classReader = new ClassReader("sample.HelloWorld");
        System.out.println(classReader.getClassName());
        System.out.println(classReader.getAccess());
        System.out.println(classReader.getSuperName());
        System.out.println(Arrays.toString(classReader.getInterfaces()));
    }


    /**
     * accept()方法，这个方法接收一个ClassVisitor类型的参数，因此accept()方法是将ClassReader和ClassVisitor进行连接的“桥梁”。
     * accept()方法的代码逻辑就是按照一定的顺序来调用ClassVisitor当中的visitXxx()方法。
     *
     * 流程：
     *      .class --> ClassReader --> ClassVisitor1 ... --> ClassVisitorN --> ClassWriter --> .class文件
     *      第一步，构建ClassReader。生成的ClassReader对象，它是这条“河流”的“源头”。
     *      第二步，构建ClassWriter。生成的ClassWriter对象，它是这条“河流”的“归处”，它可以想像成是“百川东到海”中的“大海”。
     *      第三步，串连ClassVisitor。生成的ClassVisitor对象，它是这条“河流”上的重要节点，可以想像成一个“水库”；
     *          可以有多个ClassVisitor对象，也就是在这条“河流”上存在多个“水库”，这些“水库”可以对“河水”进行一些处理，最终会这些“水库”的水会流向“大海”；
     *          也就是说多个ClassVisitor对象最终会连接到ClassWriter对象上。
     *      第四步，结合ClassReader和ClassVisitor。在ClassReader类上，有一个accept()方法，它接收一个ClassVisitor类型的对象；
     *          换句话说，就是将“河流”的“源头”和后续的“水库”连接起来。
     *      第五步，生成byte[]。到这一步，就是所有的“河水”都流入ClassWriter这个“大海”当中，这个时候我们调用ClassWriter.toByteArray()方法，
     *          就能够得到byte[]内容。
     */
    @Test
    public void accept() throws IOException {
        String relativePath = "sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relativePath);
        byte[] bytes1 = FileUtils.readBytes(filepath);

        //（1）构建ClassReader
        assert bytes1 != null;
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new ClassVisitor(api, cw) { /**/
        };

        //（4）结合ClassReader和ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);
    }

    /**
     * parsingOptions参数可以选取的值有以下5个：
     *
     * 0                             会生成所有的ASM代码，包括调试信息、frame信息和代码信息。
     * ClassReader.SKIP_CODE         会忽略代码信息，例如，会忽略对于MethodVisitor.visitXxxInsn()方法的调用。
     * ClassReader.SKIP_DEBUG        会忽略调试信息，例如，会忽略对于MethodVisitor.visitParameter()、MethodVisitor.visitLineNumber()和MethodVisitor.visitLocalVariable()等方法的调用。
     * ClassReader.SKIP_FRAMES       会忽略frame信息，例如，会忽略对于MethodVisitor.visitFrame()方法的调用。
     * ClassReader.EXPAND_FRAMES     会对frame信息进行扩展，例如，会对MethodVisitor.visitFrame()方法的参数有影响。
     *
     * 推荐使用：
     *      在调用ClassReader.accept()方法时，其中的parsingOptions参数，推荐使用ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES。
     *      在创建ClassWriter对象时，其中的flags参数，推荐使用ClassWriter.COMPUTE_FRAMES。
     *
     * 使用ClassReader.SKIP_DEBUG的时候，就不会生成调试信息。
     *      因为这些调试信息主要是记录某一条instruction在代码当中的行数，以及变量的名字等信息；
     *      如果没有这些调试信息，也不会影响程序的正常运行，也就是说功能不受影响，因此省略这些信息，就会让ASM代码尽可能的简洁。
     *
     * 使用ClassReader.SKIP_FRAMES的时候，就会忽略frame的信息。
     *      为什么要忽略这些frame信息呢？因为frame计算的细节会很繁琐，需要处理的情况也有很多，总的来说，就是比较麻烦。
     *      我们解决这个麻烦的方式，就是让ASM帮助我们来计算frame的情况，也就是在创建ClassWriter对象的时候使用ClassWriter.COMPUTE_FRAMES选项。
     *
     * 在刚开始学习ASM的时候，对于parsingOptions参数，我们推荐使用ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES的组合值。
     * 但是，以后，随着对ASM的知识越来越熟悉，或者随着功能需求的变化，可以尝试着使用其它的选项值。
     */
    @Test
    public void parsingOptions() throws IOException {
        // 设置参数
        String classname = "sample.HelloWorld";
        int parsingOptions = ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG;

        // 改变这个值，查看打印的asm代码有什么不同，结合上述描述，可轻易的观察出
        parsingOptions = ClassReader.SKIP_CODE;

        boolean asmCode = true;
        // 打印结果
        Printer printer = asmCode ? new ASMifier() : new Textifier();
        PrintWriter printWriter = new PrintWriter(System.out, true);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(classname).accept(traceClassVisitor, parsingOptions);
    }
}
