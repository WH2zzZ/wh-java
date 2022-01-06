package com.oowanghan.spring.factorybean;

import com.oowanghan.spring.bean.ObjectInterface;
import com.oowanghan.spring.bean.UserInterface;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author WangHan
 * @Create 2021/12/14 1:55 上午
 */
@ComponentScan({"com.oowanghan.spring.bean", "com.oowanghan.spring.factorybean"})
public class FactoryBeanApplication {

    public static void main(String[] args) {
        // 方案1
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserService.class);
//        UserService userService = applicationContext.getBean("userService", UserService.class);
//        userService.test();

        // 方案2, 这种方案让factoryBean更动态
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(FactoryBeanApplication.class);

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(DefaultFactoryBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserInterface.class);
        applicationContext.registerBeanDefinition("userInterface", beanDefinition);

        AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition1.setBeanClass(DefaultFactoryBean.class);
        beanDefinition1.getConstructorArgumentValues().addGenericArgumentValue(ObjectInterface.class);
        applicationContext.registerBeanDefinition("xxx", beanDefinition1);

        applicationContext.refresh();

        DefaultService defaultService = applicationContext.getBean("defaultService", DefaultService.class);
        defaultService.test();

        // 方案3 见importBean
    }
}
