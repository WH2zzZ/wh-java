package com.oowanghan.spring.bean;

import com.oowanghan.spring.scop.MobileRequestScope;
import org.springframework.stereotype.Component;

/**
 * @Author WangHan
 * @Create 2021/12/7 12:47 上午
 */
@Component
@MobileRequestScope
public class User implements UserInterface{

    @Override
    public void test() {
        System.out.println("test");
    }
}
