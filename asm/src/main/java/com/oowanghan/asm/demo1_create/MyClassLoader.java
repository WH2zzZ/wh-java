package com.oowanghan.asm.demo1_create;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("com.oowanghan.asm.demo1_create.HelloWorld".equals(name)) {
            byte[] bytes = HelloWorldDump.dump();
            Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
            return clazz;
        }

        throw new ClassNotFoundException("Class Not Found: " + name);
    }
}