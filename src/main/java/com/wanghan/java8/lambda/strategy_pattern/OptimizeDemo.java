package com.wanghan.java8.lambda.strategy_pattern;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 优化demo
 * 需求：1.查找出年龄大于40岁
 *      2.查找出工资小于5000的
 */
public class OptimizeDemo {

    List<Employee> employeeList = Arrays.asList(
        new Employee("张三",20,1000),
        new Employee("李四",30,3000),
        new Employee("王五",40,5000),
        new Employee("赵六",50,7000),
        new Employee("钱七",60,9000));

    //最low的完成该需求的方式
    @Test
    public void demo01(){
        //1.查找出年龄大于40岁
        List<Employee> resultAge = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getAge() > 40){
                resultAge.add(employee);
            }
        }
        for (Employee employee : resultAge) {
            System.out.println("年龄大于40：" + employee);
        }
        //2.查找出工资小于5000的
        List<Employee> resultSalary = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getSalary() < 5000){
                resultSalary.add(employee);
            }
        }
        for (Employee employee : resultAge) {
            System.out.println("工资小于5000：" + employee);
        }
    }

    //使用策略模式（strategy_pattern）
    @Test
    public void demo02(){
        //年龄大于40
        List<Employee> ageFilter = this.filterEmployee(employeeList, new AgeFilter());
        for (Employee employee : ageFilter) {
            System.out.println("年龄过滤：" + employee);
        }
        //工资大于5000
        List<Employee> salaryFilter = this.filterEmployee(employeeList, new SalaryFilter());
        for (Employee employee : salaryFilter) {
            System.out.println("工资过滤：" + employee);
        }
    }

    //对相同代码进行抽取
    private List<Employee> filterEmployee(List<Employee> employeeList, MyFilter<Employee> filter){
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employeeList) {
            //对相同的代码中同一位置可能会出现的不同代码进行策略抽取
            if (filter.filter(employee)){
                result.add(employee);
            }
        }
        return result;
    }

    //使用Lambda表达式优化StrategyPattern
    @Test
    public void demo03(){
        //年龄大于40
        List<Employee> ageFilter = this.filterEmployee(employeeList, employee -> employee.getAge() > 40);
        ageFilter.forEach(System.out::println);
        //工资小于5000
        List<Employee> salaryFilter = this.filterEmployee(employeeList, employee -> employee.getSalary() < 5000);
        salaryFilter.forEach(System.out::println);

    }

    //使用StreamAPI优化StrategyPattern(后面详细讲，这里简单介绍一下)
    @Test
    public void demo04(){
        //年龄大于40
        employeeList.stream()
                    .filter(employee -> employee.getAge() > 40)
                    .forEach(System.out::println);
        System.out.println("----------------------------------");
        //工资小于5000
        employeeList.stream()
                .filter(employee -> employee.getSalary() < 5000)
                .forEach(System.out::println);
        System.out.println("----------------------------------");
        //限制打印个数
        employeeList.stream()
                .filter(employee -> employee.getSalary() < 5000)
                .limit(1)
                .forEach(System.out::println);
        System.out.println("----------------------------------");
        //限制打印输出字段
        employeeList.stream()
                    .map(Employee::getName)
                    .forEach(System.out::println);
        System.out.println("----------------------------------");
    }

}



