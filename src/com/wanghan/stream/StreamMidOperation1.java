package com.wanghan.stream;

import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 * 注意:中间操作不做任何执行,只有碰到终止操作的时候才会一次性执行全部内容. 即"惰性求值"
 * 筛选与切片
 * filter -- 接受Lambda, 从流中排除某些元素
 * limit -- 截断流,使其元素不超过给定数量
 * skip(n) -- 跳过元素,放回一个扔掉前 n 个元素的流. 若流中元素不足 n 个,则返回一个空流.与limit(n)互补
 * distinct -- 筛选,通过流所生成元素的hashCode() 和 equals() 去去除重复元素
 */
public class StreamMidOperation1 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 20, 2000),
            new Employee("zhangsan", 21, 3000),
            new Employee("zhangsan", 22, 4000),
            new Employee("zhangsan", 23, 5000),
            new Employee("zhangsan", 24, 6000),
            new Employee("zhangsan", 24, 6000),
            new Employee("zhangsan", 24, 6000)
    );

    /**
     * filter -- 接受Lambda, 从流中排除某些元素.
     * 内部迭代 :会打印多次输出语句
     */
    @Test
    public void test1(){
        Stream<Employee> employeeStream = employees.stream().filter(e -> {
            System.out.println("Stream API的中间操作执行了");
            return e.getAge() > 22;
        });

        //终止操作(打上注释可以查看中间操作在终止操作之前是不会执行的)
        employeeStream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test2(){
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * limit -- 截断流,使其元素不超过给定数量
     */
    @Test
    public void test3(){
        employees.stream()
                 .limit(3)
                 .forEach(System.out::println);
    }

    /**
     * skip(n) -- 跳过元素,放回一个扔掉前 n 个元素的流. 若流中元素不足 n 个,则返回一个空流.与limit(n)互补
     */
    @Test
    public void test4(){
        employees.stream()
                 .skip(2)
                 .forEach(System.out::println);
    }

    /**
     * distinct -- 筛选,通过流所生成元素的hashCode() 和 equals() 去去除重复元素(需要重写hashcode 和 equals方法)
     */
    @Test
    public void test5(){
        employees.stream()
                 .distinct()
                 .forEach(System.out::println);
    }

}
