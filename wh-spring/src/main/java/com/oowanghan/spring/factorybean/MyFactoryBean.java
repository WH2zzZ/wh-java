package com.oowanghan.spring.factorybean;

import com.oowanghan.spring.bean.Person;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author WangHan
 * @Create 2021/12/7 12:56 上午
 */
@Component("person")
public class MyFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<Person> getObjectType() {
        return Person.class;
    }
}
