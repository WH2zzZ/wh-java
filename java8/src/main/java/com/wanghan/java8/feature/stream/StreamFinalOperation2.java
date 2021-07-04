package com.wanghan.java8.feature.stream;

import com.wanghan.java8.feature.lambda.strategy_pattern.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * 规约:
 * reduce(T identity, BinaryOperator)/ reduce(BinaryOperator) --可以将流中元素反复结合起来,得到一个值
 */
public class StreamFinalOperation2 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 20, 2000, Employee.Status.BUSY),
            new Employee("zhangsan", 21, 3000, Employee.Status.FREE),
            new Employee("zhangsan", 22, 4000, Employee.Status.VOCATION),
            new Employee("zhangsan", 23, 5000, Employee.Status.FREE),
            new Employee("zhangsan", 24, 6000, Employee.Status.VOCATION),
            new Employee("zhangsan", 24, 6000, Employee.Status.BUSY),
            new Employee("zhangsan", 24, 6000, Employee.Status.FREE)
    );

    /**
     * 规约: 1. 将起始值identity作为起始值,最开始将其实质作为下x, y作为从集合流中取出的第一个元素
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer num = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(num);

        Optional<Integer> count = employees.stream().map(Employee::getSalary).reduce(Integer::sum);
        System.out.println(count.get());
    }

    /**
     * 收集
     * collect--将流转换为其他形式,接受一个Collection接口的实现,用于给Stream中元素做汇总的方法
     */
    @Test
    public void test4(){
        List<String> nameList = employees.stream().map(Employee::getName).collect(Collectors.toList());
        nameList.forEach(System.out::println);

        System.out.println("--------------------------");
        Set<String> nameSet = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        nameSet.forEach(System.out::println);

        System.out.println("--------------------------");
        HashSet<String> collect = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void test5(){
        //计数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);

        //平均值
        Double avg = employees.stream().collect(Collectors.averagingInt(Employee::getAge));
        System.out.println(avg);

        //总和
        IntSummaryStatistics summaryStatistics = employees.stream().collect(Collectors.summarizingInt(Employee::getAge));
        System.out.println(summaryStatistics);

        //最大值
        employees.stream().map(Employee::getAge).collect(Collectors.maxBy(Integer::compareTo));

        //最小值
        employees.stream().map(Employee::getAge).collect(Collectors.minBy(Integer::compareTo));

        //other方式:
        double average = summaryStatistics.getAverage();
        long count1 = summaryStatistics.getCount();
        int max = summaryStatistics.getMax();
        int min = summaryStatistics.getMin();
        long sum = summaryStatistics.getSum();

        //连接
        String s = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(s);
    }

    //分组
    @Test
    public void test6(){
        Map<Employee.Status, List<Employee>> statusListMap = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));

        statusListMap.forEach((status, employeeList) -> System.out.println(status + " : " + employeeList));
    }

    //多级分组
    @Test
    public void test7(){
        Map<Employee.Status, Map<String, List<Employee>>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(o -> {
            if (o.getAge() <= 23) {
                return "青年";
            } else {
                return "中年";
            }
        })));

        collect.forEach((status, stringListMap) -> {
            stringListMap.forEach((s, employeeList) -> {
                System.out.println(status + " : " + s + " : " + employeeList);
            });
        });
    }

    //分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 23));
        collect.forEach((aBoolean, employeeList) -> System.out.println(aBoolean + " : " + employeeList));
    }


    @Test
    public void test9(){
        String a = "/5fe52e9e1470478559d3280598a1ffa4.PROJECT/6000000704562544.COMPANY/6000001481976524.DEPT";
        String[] split = a.split("/");
        for (String s : split) {
            if (s.contains("COMPANY")){
                String substring = s.substring(0, s.indexOf("."));
                System.out.     println(substring);
            }
        }
    }

}
