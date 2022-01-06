package com.oowanghan.spring;

import com.oowanghan.spring.bean.Member;
import com.oowanghan.spring.bean.Person;
import com.oowanghan.spring.bean.User;
import com.oowanghan.spring.config.MyConfig;
import com.oowanghan.spring.factorybean.MyFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author WangHan
 * @Create 2021/12/7 12:39 上午
 */
public class Main {

    public static void main(String[] args) {
        // 从spring.xml方式获取bean
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        // 注解@Bean方式获取, @Component
        AnnotationConfigApplicationContext annotationApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        // beanDefinition
        // 编程式
        // 声明式
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        annotationApplicationContext.registerBeanDefinition("user", beanDefinition);
        User user = annotationApplicationContext.getBean("user", User.class);
        System.out.println(user);

        // factoryBean
        Person person = annotationApplicationContext.getBean("person", Person.class);
        System.out.println(person);
        MyFactoryBean myFactoryBean = annotationApplicationContext.getBean("&person", MyFactoryBean.class);
        System.out.println(myFactoryBean);

        // supplier 获取bean
        annotationApplicationContext.registerBean(Member.class);
        Member member = annotationApplicationContext.getBean(Member.class);
        System.out.println(member);

    }


}
