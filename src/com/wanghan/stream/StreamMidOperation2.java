package com.wanghan.stream;

import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 映射
 * map -- 接受Lambda , 将元素转换成其他形式或提取信息.
 *        接受一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素.
 * flatmap -- 接受一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
 */
public class StreamMidOperation2 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 20, 2000),
            new Employee("lisi", 21, 3000),
            new Employee("zhouwu", 22, 4000),
            new Employee("zhaoliu", 23, 5000),
            new Employee("sunqian", 24, 6000),
            new Employee("wanghan", 24, 6000),
            new Employee("zhangqi", 24, 6000)
    );

    /**
     * map -- 接受Lambda , 将元素转换成其他形式或提取信息.
     *        接受一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素.
     */
    @Test
    public void test1(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
            .map(s -> s.toUpperCase())
            .forEach(System.out::println);
    }

    @Test
    public void test2(){
        employees.stream()
                 .map(Employee::getName)
                 .forEach(System.out::println);
    }

    /**
     * flatmap -- 接受一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
     */
    
}
