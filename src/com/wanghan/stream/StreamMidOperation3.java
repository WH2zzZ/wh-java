package com.wanghan.stream;


import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 排序
 * sorted() -- 自然排序 Comparable
 * sorted(Comparator com) -- 定制排序
 */
public class StreamMidOperation3 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 20, 2000),
            new Employee("zhangsan", 21, 3000),
            new Employee("zhangsan", 22, 4000),
            new Employee("zhangsan", 23, 5000),
            new Employee("zhangsan", 24, 6000),
            new Employee("zhangsan", 24, 6000),
            new Employee("zhangsan", 24, 6000)
    );

    @Test
    public void test1(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        list.stream()
            .sorted()
            .forEach(System.out::println);

        employees.stream()
            .sorted((o1, o2) -> {
                if (o1.getAge().equals(o2.getAge())){
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getAge().compareTo(o2.getAge());
            })
            .forEach(System.out::println);
    }
}
