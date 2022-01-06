package com.oowanghan.spring.bean;

import com.oowanghan.spring.config.MyConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器中
 *
 * @Author WangHan
 * @Create 2021/12/8 11:29 下午
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * bean初始化方法调用前被调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before1-Initialization: beanName = " + beanName + ", bean = " + bean);
        return bean;
    }

    /**
     * bean初始化方法调用后被调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("user")) {
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("proxy start");
                        method.invoke(bean, args);
                        System.out.println("proxy end");
                        return null;
                    });
        }
        System.out.println("after1-Initialization: beanName = " + beanName + ", bean = " + bean);
        return bean;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        UserInterface user = (UserInterface) applicationContext.getBean("user");
        user.test();
    }
}
