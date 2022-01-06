package com.oowanghan.spring.factorybean;

import com.oowanghan.spring.bean.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WangHan
 * @Create 2021/12/14 1:19 上午
 */
@Component
public class UserService {

    @Autowired
    private UserInterface userInterface;

    public void test() {
        userInterface.test();
    }
}
