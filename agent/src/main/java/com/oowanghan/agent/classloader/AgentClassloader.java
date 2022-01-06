package com.oowanghan.agent.classloader;

/**
 * @Author WangHan
 * @Create 2021/9/23 12:45 上午
 */
public class AgentClassloader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        final Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass != null) {
            return loadedClass;
        }

        try {
            Class<?> aClass = findClass(name);
            if (resolve) {
                resolveClass(aClass);
            }
            return aClass;
        } catch (Throwable t) {
            return super.loadClass(name);
        }
    }
}
