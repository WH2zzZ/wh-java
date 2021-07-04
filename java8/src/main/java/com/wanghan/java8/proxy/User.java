package com.wanghan.java8.proxy;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String name;

    private int age;

    public User(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}