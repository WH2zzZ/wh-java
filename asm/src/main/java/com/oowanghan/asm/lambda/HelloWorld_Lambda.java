package com.oowanghan.asm.lambda;

import java.util.function.Consumer;

/**
 * 查看lambda和ASM的关系
 * @Author WangHan
 * @Create 2021/6/29 1:32 上午
 */
public class HelloWorld_Lambda {

    /**
     * javap -v xxxxx 查看字节码
     * 找到如下
     * 8: invokedynamic #4,  0              // InvokeDynamic #0:accept:(Ljava/io/PrintStream;)Ljava/util/function/Consumer;
     * 可以看到这里引用了常量词4的内容
     * #4 = InvokeDynamic      #0:#35         // #0:accept:(Ljava/io/PrintStream;)Ljava/util/function/Consumer;
     * #0又是什么意思，可以查看BootstrapMethods
     * BootstrapMethods:
     *   0: #31 invokestatic
     *   java/lang/invoke/LambdaMetafactory.metafactory:
     *      (
     *          Ljava/lang/invoke/MethodHandles$Lookup;
     *          Ljava/lang/String;Ljava/lang/invoke/MethodType;
     *          Ljava/lang/invoke/MethodType;
     *          Ljava/lang/invoke/MethodHandle;
     *          Ljava/lang/invoke/MethodType;
 *          )
     *      Ljava/lang/invoke/CallSite;
     * @param args
     */
    public static void main(String[] args) {
        Consumer<String> c = System.out::println;
        c.accept("hello world");
    }
}
