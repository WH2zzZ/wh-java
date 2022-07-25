package com.oowanghan.io.nio.buffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区：在Java NIO中负责数据的存取，缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型的不同（不包括boolean），提供相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 缓冲区存取数据的两个核心方法
 * put():存入数据到缓冲区
 * get():取出数据从缓冲区
 *
 * Buffer中四个核心属性
 * mark：标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置
 * position：位置，表示缓冲区中正在操作数据的位置
 * limit：界限，表示缓冲区中可以操作数据的大小。一旦声明不能改变
 * capacity：容量，表示缓冲区中最大的存储数据的容量，一旦声明不能改变
 *
 * 直接缓冲区和非直接缓冲区
 * 非直接缓冲区：通过allocate()方法分配到缓冲区，将缓冲区建立在jvm的内存中
 * 直接缓冲区：通过allocateDirect()方法分配到直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 *
 * @Author WangHan
 * @Create 2019/9/15 4:07 下午
 */
public class BufferDemo {

    @Test
    public void testInitBuffer(){
        //1.分配制定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        printBufferInfo(byteBuffer);

        //2.存数据
        String data1 = "1";
        byteBuffer.put(data1.getBytes());
        printBufferInfo(byteBuffer);

        //3.读数据模式，就是将limit位置放到position位置
        byteBuffer.flip();
        printBufferInfo(byteBuffer);

        //4.开始读
        byte[] content = new byte[byteBuffer.limit()];
        byteBuffer.get(content);
        System.out.println("读到的数据：" + new String(content, 0, content.length));
        printBufferInfo(byteBuffer);

        //5.可重复读,将position放到缓冲区的最前面，可操作数据为1
        byteBuffer.rewind();
        printBufferInfo(byteBuffer);

        //6.清空缓冲区(缓冲区的数据一直存在，只是数据被遗忘了)
        byteBuffer.clear();
        printBufferInfo(byteBuffer);
        System.out.println(byteBuffer.get());
    }

    @Test
    public void testReset(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //判断缓冲区是否还有剩余的数据，相当于position到limit的数据长度
        if (byteBuffer.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(byteBuffer.remaining());
        }

        String data = "12345";
        byteBuffer.put(data.getBytes());
        printBufferInfo(byteBuffer);

        byteBuffer.flip();
        printBufferInfo(byteBuffer);
        byte[] content = new byte[byteBuffer.limit()];
        byteBuffer.get(content,0,2);

        printBufferInfo(byteBuffer);
        System.out.println("读到的数据:" + new String(content, 0, content.length));

        //1.mark标记, 配合reset使用的
        byteBuffer.mark();
        byteBuffer.get(content, 2, 2);
        printBufferInfo(byteBuffer);
        System.out.println("mark读到的数据:" + new String(content, 0, content.length));

        //2.reset
        byteBuffer.reset();
        printBufferInfo(byteBuffer);

        //判断缓冲区是否还有剩余的数据
        if (byteBuffer.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(byteBuffer.remaining());
        }
    }

    @Test
    public void testByteBufferDirect(){
        ByteBuffer nonDirectBuffer = ByteBuffer.allocateDirect(1024);
        //判断是否为直接缓冲区
        System.out.println(nonDirectBuffer.isDirect());
    }

    private void printBufferInfo(ByteBuffer byteBuffer) {
        System.out.println("------------------------------------------");
        System.out.println("缓冲区当前位置：" + byteBuffer.position());
        System.out.println("缓冲区可以操作数据的大小：" + byteBuffer.limit());
        System.out.println("缓冲区最大的存储数据的容量：" + byteBuffer.capacity());
    }
}
