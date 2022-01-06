package com.oowanghan.io.nio;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.SortedMap;

/**
 * 用于源节点与目标节点的连接。在javaNIO中负责缓冲区中数据的传输，Channel本身不存储数据
 * Java.nio.channels.Channel接口
 *     |--FileChannel
 *     |--SocketChannel
 *     |--ServerSocketChannel
 *     |--DatagramChannel
 * 获取通道
 * 1.Java争对支持通道的类提供了getChannel()方法
 *   本地IO：
 *   FileInputStream/FileOutputStream
 *   RandomAccessFile
 *   网络IO:
 *   Socket
 *   ServerSocket
 *   DatagramSocket
 * 2.在jdk1.7中的NIO.2争对各个通道提供了静态方法open()
 * 3.在jdk1.7中的NIO.2的Files工具类的newByteChannel()
 *
 * 通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 分散Scatter与聚集Gather
 * 分散读取（Scatter Reads）:将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）:将多个缓冲区中的数据聚集到通道中
 *
 * 字符集：charset
 *
 * @Author WangHan
 * @Create 2019/9/15 5:29 下午
 */
public class ChannelDemo {

    /**
     * 通道之间数据写入到buffer中，才可读取相应的数据，使用非直接缓冲区方式
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        //使用通道完成文件的复制（非直接缓冲区）
        FileInputStream fis = new FileInputStream("3.jpg");
        FileOutputStream fos = new FileOutputStream("4.jpg");

        //获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //分配制定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //将通道中的数据存入缓冲区中
        while (inChannel.read(buffer) != -1){
            //切换到读数据模式
            buffer.flip();
            //将缓冲区中的数据写入到通道中
            outChannel.write(buffer);
            //清空缓冲区，以便下次写
            buffer.clear();
        }

        fis.close();
        fos.close();
        inChannel.close();
        outChannel.close();
    }

    /**
     * 比test1速度更快，使用的内存映射，比较占用系统内存
     */
    @Test
    public void test2() throws IOException {
        //获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                //CREATE_NEW文件存在就报错
                StandardOpenOption.CREATE);

        //内存映射文件（直接缓冲区），如果垃圾回收机制不回收这个内存映射，资源一直都释放不掉
        MappedByteBuffer inMapperBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //不像test1，这种方式直接对缓冲区进行数据的读写操作
        byte[] result = new byte[inMapperBuf.limit()];
        inMapperBuf.get(result);
        outMapperBuf.put(result);

        inChannel.close();
        outChannel.close();
    }

    /**
     * 通道之间的数据传输，也是使用直接缓冲区的方式
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                //CREATE_NEW文件存在就报错
                StandardOpenOption.CREATE);

        //底层用的就是MapperByteBuffer
        //inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    /**
     * 分散与聚集
     */
    @Test
    public void test04() throws IOException {
        //读写模式
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        //1.获取channel
        FileChannel channel = raf1.getChannel();

        //2.分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(100);

        //3.分散读取
        ByteBuffer[] buffers = {buffer1, buffer2};
        channel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }

        //4.聚集写入
        RandomAccessFile outFile = new RandomAccessFile("2.txt", "rw");
        FileChannel outChannel = outFile.getChannel();

        outChannel.write(buffers);
    }

    /**
     * 字符集
     */
    @Test
    public void test05() throws CharacterCodingException {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        charsets.forEach((s, charset) -> {
            System.out.println(s + "->" + charset);
        });

        Charset gbk = Charset.forName("gbk");

        //获取编码器和解码器
        CharsetEncoder gbkEncoder = gbk.newEncoder();
        CharsetDecoder gbkDecoder = gbk.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("王晗好帅");
        charBuffer.flip();

        //编码
        ByteBuffer byteBuffer = gbkEncoder.encode(charBuffer);

        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        CharBuffer charBufferResult = gbk.decode(byteBuffer);
        System.out.println(charBufferResult.toString());

    }
}
