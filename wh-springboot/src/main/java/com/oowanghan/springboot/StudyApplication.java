package com.oowanghan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {

	//SpringApplication会将定义的启动类传入
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
