package com.wanghan.java8.feature.stream;

import com.wanghan.java8.feature.lambda.strategy_pattern.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void test9(){
        employees.stream().forEach(employee -> {
            if (employee.getName().equals("wanghan")) {
                return;
            }
            System.out.println(employee.getName());
        });
        System.out.println(employees);
    }

    /**
     * flatmap -- 接受一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
     */
    public static Stream<Character> filterString(String str){
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }

    @Test
    public void test3(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        //使用map就是只能Stream中镶嵌String
        Stream<Stream<Character>> stream = list.stream()
                .map(StreamMidOperation2::filterString);

        //使用flatmap,就自动将两个Stream整合在一起
        Stream<Character> stream1 = list.stream().flatMap(StreamMidOperation2::filterString);

        List<Stream<Character>> collect = stream.collect(Collectors.toList());
        System.out.println(collect);

        List<Character> collect1 = stream1.collect(Collectors.toList());
        System.out.println(collect1);
    }
    
}
