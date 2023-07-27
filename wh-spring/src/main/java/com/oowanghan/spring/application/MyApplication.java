package com.oowanghan.spring.application;

import com.oowanghan.spring.bean.User;
import com.oowanghan.spring.factorybean.UserService;
import com.oowanghan.spring.scop.CustomBeanFactoryPostProcessor;
import com.oowanghan.spring.scop.MobileRequestScopeFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WangHan
 * @Create 2021/12/14 12:32 上午
 */
@ComponentScan("com.oowanghan.spring")
public class MyApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyApplication.class);
        applicationContext.refresh();

        final UserService bean = applicationContext.getBean(UserService.class);

        bean.test();

    }

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }
}
