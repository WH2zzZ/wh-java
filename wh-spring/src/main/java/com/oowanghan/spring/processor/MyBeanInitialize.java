package com.oowanghan.spring.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WangHan
 * @Create 2021/12/10 12:14 上午
 */
@Component
public class MyBeanInitialize implements InitializingBean {

    @Autowired
    private UserInterface user;

    /**
     * 初始化之后才会执行
     * 一般会通过这个方法来验证属性
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (user != null) {
            System.out.println("验证成功");
        }
        System.out.println("初始化");
    }
}
