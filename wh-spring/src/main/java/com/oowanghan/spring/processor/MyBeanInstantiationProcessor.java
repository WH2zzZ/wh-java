package com.oowanghan.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 实例化前处理器，BeanPostProcess
 *
 * @Author WangHan
 * @Create 2021/12/8 11:52 下午
 */
@Component
public class MyBeanInstantiationProcessor implements InstantiationAwareBeanPostProcessor {


    /**
     * 在实例化之前会先判断有没有自定义InstantiationAwareBeanPostProcessor， 如果有会执行postProcessBeforeInstantiation
     * 执行了就会判断，返回的bean如果不为空，那么bean的实例化和初始化前置处理全部结束，直接执行后置处理
     *
     * protected Object resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd) {
     * 		Object bean = null;
     * 		if (!Boolean.FALSE.equals(mbd.beforeInstantiationResolved)) {
     * 			// Make sure bean class is actually resolved at this point.
     * 			if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
     * 				Class<?> targetType = determineTargetType(beanName, mbd);
     * 				if (targetType != null) {
     * 					bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
     * 					if (bean != null) {
     * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
     *                                        }* 				}* 			}
     * 			mbd.beforeInstantiationResolved = (bean != null);
     *        }
     * 		return bean;* 	}
     */
    /**
     * bean实例化之前调用
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("before-Instantiation : beanName = " + beanName + ", beanClass = " + beanClass);
        if (beanClass.equals(User.class)) {
            // 这里如果直接returnUser， 会使其他的前置处理器失效
            return new User();
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    /**
     * 如果return false， 则不会设置属性
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }
}
