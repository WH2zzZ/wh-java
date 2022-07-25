package com.oowanghan.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author WangHan
 * @Create 2021/12/14 1:24 上午
 */
public class DefaultFactoryBean implements FactoryBean {

    private final Class mapperClass;

    public DefaultFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public Object getObject() throws Exception {

        return Proxy.newProxyInstance(
                DefaultFactoryBean.class.getClassLoader(),
                new Class[]{mapperClass},
                (proxy, method, args) -> {
                    System.out.println(mapperClass.getName() + " ： run");
                    return null;
                });
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }
}
