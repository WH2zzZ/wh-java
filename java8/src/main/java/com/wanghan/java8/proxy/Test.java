package com.wanghan.java8.proxy;

/**
 * @Author WangHan
 * @Create 2020/9/9 10:48 上午
 */
public class Test {

    public static void main(String[] args) {
        MapperProxy mapperProxy = new MapperProxy();
        UserMapper userMapper = mapperProxy.newInstance(UserMapper.class);

        User user = userMapper.getUserById(100);

    }
}
