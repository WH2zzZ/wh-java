package com.wanghan.java8.jvm.classload.test;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @className: MyClassloader1
 * @author: wanghan
 * @date: 2022-01-25 22:30
 **/
public class MyClassloader1 extends ClassLoader {

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.contains("TestB")) {
            return getClassFile(name);
        }
        return super.findClass(name);
    }

    @SneakyThrows
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.contains("TestB")) {
            final Class<?> classFile = getClassFile(name);
            return classFile;
        }
        return super.loadClass(name);
    }

    private Class<?> getClassFile(String name) throws IOException {

        File file = new File("/Users/wanghan/IdeaProjects/trip/wh-java/java8/target/classes/com/wanghan/java8/jvm/classload/test/TestB.class");

        byte[] bytes;
        try (
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream(file)) {

            int ch;
            while ((ch = fis.read()) != -1) {
                byteOutStream.write(ch);
            }
            bytes = byteOutStream.toByteArray();
        }

        return this.defineClass(name, bytes, 0, bytes.length);
    }
}
