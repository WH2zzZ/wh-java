package com.wanghan.java8.stream;


import com.wanghan.java8.lambda.strategy_pattern.Employee;
import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.*;

/**
 * 排序
 * sorted() -- 自然排序 Comparable
 * sorted(Comparator com) -- 定制排序
 */
public class StreamMidOperation3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 20, 2000),
            new Employee("啊", 21, 3000),
            new Employee("李四", 22, 4000),
            new Employee("笔", 23, 5000),
            new Employee("曹", 24, 6000),
            new Employee("的", 24, 6000),
            new Employee("哥", 24, 6000),null,null
    );

    @Test
    public void test1(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        list.stream()
            .sorted()
            .forEach(System.out::println);

        /**
         * 根据汉字顺序排序
         */
        Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
        employees.stream()
            .sorted((o1, o2) -> {
//                if (o1.getAge().equals(o2.getAge())){
//                    return o1.getName().compareTo(o2.getName());
//                }
//                return o1.getAge().compareTo(o2.getAge());
                return comparator.compare(o1.getName(), o2.getName());
            })
            .forEach(System.out::println);
        System.out.println("ss".contains("ss"));
//        System.out.println(employees);
    }

    @Test
    public void test2(){
        Map<String, String> map = new HashMap<>();

        String haha = map.get("haha");
        System.out.println(haha);
    }

}
