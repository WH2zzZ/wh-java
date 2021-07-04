package com.wanghan.java8.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
 
public class TestMain {
 
	public static void main(final String[] arguments) throws Exception {
		Class<?> clazz = TestMain.class;
		Method method = clazz.getDeclaredMethod("test", String.class, int.class);
		System.out.print("test : ");
		Parameter[] parameters = method.getParameters();
		for (final Parameter parameter : parameters) {
			if (parameter.isNamePresent()) {
				System.out.print(parameter.getName() + ' ');
			}
		}
	}
 
	public void test(String param1, int param2) {
		System.out.println(param1 + param2);
	}
}
