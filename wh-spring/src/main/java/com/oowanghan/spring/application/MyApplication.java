package com.oowanghan.spring.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author WangHan
 * @Create 2021/12/14 12:32 上午
 */
@ComponentScan("com.wanghan.spring")
public class MyApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyApplication.class);
        applicationContext.refresh();

    }
}
