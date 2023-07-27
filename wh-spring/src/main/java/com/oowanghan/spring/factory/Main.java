package com.oowanghan.spring.factory;

import com.oowanghan.spring.bean.User;
import com.oowanghan.spring.scop.MobileRequestScopeFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author WangHan
 * @Create 2021/12/8 12:33 上午
 */
public class Main {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("user", new User());
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        // ApplicationContext 继承了 BeanFactory，也继承了很多其他功能的接口， 所以说ApplicationContext功能更多
    }
}
