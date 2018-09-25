package com.wanghan.lambda.reference;

import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 *
 * 格式: ClassName :: new
 */
public class ConstructorReference {

    /**
     * ClassName :: new
     * 规则: 需要调用的构造器的参数列表与函数式接口的构造器列表参数需要保持一致
     *
     */
    @Test
    public void test1(){
        Supplier<Employee> supplier = () -> new Employee("zhangsan", 22, 2000);

        //构造器引用方式
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee = supplier.get();
        System.out.println(employee);
    }

    @Test
    public void test2(){
        Function<String, Employee> function = (x) -> new Employee(x);

        //构造器引用方式
        Function<String, Employee> function1 = Employee::new;
        Employee employee = function1.apply("lisi");
        System.out.println(employee);
    }
}
