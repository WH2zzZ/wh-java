package com.wanghan.java8.spring.ioc.demo01;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author WangHan
 * @Create 2020/5/26 9:34 下午
 */
public class test {

    private static ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<>(16);
    private static ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>(16);

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class[] classes = {A.class, B.class};

        for (Class aClass : classes) {
            getBean(aClass);
        }
        System.out.println(cacheMap);
    }

    public static <T> T getBean(Class<T> beanClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String beanName = beanClass.getSimpleName().toLowerCase();
        if (cacheMap.containsKey(beanName)){
            return (T) cacheMap.get(beanName);
        }

        T instance = beanClass.getDeclaredConstructor().newInstance();
        cacheMap.put(beanName, instance);
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            field.set(instance, cacheMap.containsKey(fieldBeanName)
                    ? cacheMap.get(fieldBeanName) : getBean(fieldClass));
        }
        return instance;
    }
}
