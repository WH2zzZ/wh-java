package com.wanghan.stream;

import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 终止操作
 */
public class StreamFinalOperation1 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 20, 2000, Employee.Status.BUSY),
            new Employee("zhangsan", 21, 3000, Employee.Status.FREE),
            new Employee("zhangsan", 22, 4000, Employee.Status.VOCATION),
            new Employee("zhangsan", 23, 5000, Employee.Status.FREE),
            new Employee("zhangsan", 24, 6000, Employee.Status.VOCATION),
            new Employee("zhangsan", 24, 6000, Employee.Status.BUSY),
            new Employee("zhangsan", 24, 6000, Employee.Status.FREE));

    /**
     * 查找与匹配
     * allMatch - 检查是否匹配所有元素
     * anyMatch - 检查是否至少匹配一个元素
     * noneMatch - 检查是否没有匹配所有元素
     * findFirst - 放回第一个元素
     * findAny - 放回当前流中的任意元素
     * count - 放回流中元素的总数
     * max - 放回流中最大值
     * min - 放回流中最小值
     */
    @Test
    public void test1(){
        boolean b1 = employees.stream().allMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employees.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b2);

        boolean b3 = employees.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        //Optional防止空指针异常,对于放回的值有可能为空,就会放入在Optional中去
        //查找第一个,就有可能为空
        Optional<Employee> first = employees.stream().sorted(Comparator.comparing(Employee::getName)).findFirst();
        //如果为空,就替代对象
        first.orElse(employees.get(1));
        System.out.println(first.get());

        //并行流,多线程去流查找,谁先找到放回谁
        Optional<Employee> any = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(any.get());

        long count = employees.stream().count();
        System.out.println(count);

        Optional<Employee> max = employees.stream().max(Comparator.comparing(Employee::getAge));
        System.out.println(max.get());

        Optional<Integer> min = employees.stream().map(Employee::getSalary).min(Integer::compare);
        System.out.println(min.get());
    }

}
