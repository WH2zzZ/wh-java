package com.oowanghan.springboot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 自定义run方法的2种方式
 */
@Component
public class MyRunner implements ApplicationRunner, CommandLineRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(" 我是自定义的run方法1，实现 ApplicationRunner 接口既可运行"        );
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(" 我是自定义的run方法2，实现 CommandLineRunner 接口既可运行"        );
    }
}