package com.oowanghan.spring.factorybean;

import com.oowanghan.spring.bean.ObjectInterface;
import com.oowanghan.spring.bean.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WangHan
 * @Create 2021/12/14 1:38 上午
 */
@Component
public class DefaultService {

    @Autowired
    private ObjectInterface objectInterface;
    @Autowired
    private UserInterface userInterface;

    public void test() {
        objectInterface.test();
        userInterface.test();
    }
}
