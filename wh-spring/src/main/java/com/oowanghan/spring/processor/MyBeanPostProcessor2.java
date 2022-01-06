package com.oowanghan.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器中
 * @Author WangHan
 * @Create 2021/12/8 11:29 下午
 */
@Component
public class MyBeanPostProcessor2 implements BeanPostProcessor {

    /**
     * bean初始化方法调用前被调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before2-Initialization: beanName = " + beanName + ", bean = " + bean);
        return bean;
    }

    /**
     * bean初始化方法调用后被调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after2-Initialization: beanName = " + beanName + ", bean = " + bean);
        System.out.println("==================");
        return bean;
    }

}
