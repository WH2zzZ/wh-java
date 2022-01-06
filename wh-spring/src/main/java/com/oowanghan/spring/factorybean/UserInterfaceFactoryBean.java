package com.oowanghan.spring.factorybean;

import com.oowanghan.spring.bean.UserInterface;
import com.oowanghan.spring.config.MyConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author WangHan
 * @Create 2021/12/14 1:05 上午
 */
public class UserInterfaceFactoryBean implements FactoryBean<UserInterface> {
    @Override
    public UserInterface getObject() throws Exception {
        return (UserInterface) Proxy.newProxyInstance(UserInterfaceFactoryBean.class.getClassLoader(), new Class[]{UserInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy start");
                System.out.println("proxy end");
                return null;
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return UserInterface.class;
    }

}
