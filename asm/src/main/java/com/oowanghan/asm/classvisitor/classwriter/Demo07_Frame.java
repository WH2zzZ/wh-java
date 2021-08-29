package com.oowanghan.asm.classvisitor.classwriter;

/**
 *
 * Frame
 *
 * 严格的来说，每一条Instruction都对应两个frame，一个是instruction执行之前的frame，另一个是instruction执行之后的frame。
 * 但是，当多个instruction放到一起的时候来说，第n个instruction执行之后的frame，就成为第n+1个instruction执行之前的frame，
 * 所以也可以理解成：每一条instruction对应一个frame。这些frames是要存储起来的。
 * 我们知道，每一个instruction对应一个frame，如果都要存储起来，那么在.class文件中就会占用非常多的空间；
 * 而.class文件设计的一个主要目标就是尽量占用较小的存储空间，那么就需要对这些frames进行压缩。
 *
 * 如果说，instruction是按照“一个挨一个向下顺序执行”的，那么它们对应的frames就不重要；
 * 相应的，instruction在执行过程时，它是从某个地方“跳转”过来的，那么对应的frames就重要。
 *
 * 为什么说instruction是按照“一个挨一个向下顺序执行”的，那么它们对应的frames就不重要呢？因为这些instruction对应的frame可以很容易的推导出来。
 * 如果当前的instruction是从某个地方跳转过来的，就必须要记录它执行之前的frame的情况，否则就没有办法计算它执行之后的frame的情况。
 *
 * 经过压缩之后的frames，就存放在ClassFile的StackMapTable结构中。
 *
 * 为什么我们不推荐调用MethodVisitor.visitFrame()方法呢？
 * 原因是计算frame本身就很麻烦，还容易出错。
 *
 * @Author WangHan
 * @Create 2021/8/10 12:36 上午
 */
public class Demo07_Frame {


}
