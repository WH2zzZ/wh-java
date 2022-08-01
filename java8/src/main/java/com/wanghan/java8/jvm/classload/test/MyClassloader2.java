package com.wanghan.java8.jvm.classload.test;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * TODO
 *
 * @className: MyClassloader1
 * @author: wanghan
 * @date: 2022-01-25 22:30
 **/
public class MyClassloader2 extends ClassLoader{

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.contains("TestC")) {
            return getClassFile(name);
        }
        return super.findClass(name);
    }

    @SneakyThrows
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.contains("TestC")) {
            return getClassFile(name);
        }
        return super.loadClass(name);
    }

    private Class<?> getClassFile(String name) throws IOException {

        File file = new File("/Users/wanghan/IdeaProjects/trip/wh-java/java8/target/classes/com/wanghan/java8/jvm/classload/test/TestC.class");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1){
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        byte[] bytes = baos.toByteArray();
        return this.defineClass(name, bytes, 0, bytes.length);
    }
}
