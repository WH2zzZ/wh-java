package com.wanghan.optional;

import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Optional容器的常用方法
 * Optional.of(T t) : 创建一个Optional实例
 * Optional.empt() : 创建一个空的optional实例
 * Optional.ofNullable(T t) 若t不为null,创建Optional实例,否则创建空实例
 * isParesent() : 判断是否包含值
 * orElse(T t) : 如果调用对象包含值,则返回该值,否则返回t
 * orElseGet(Supplier s) : 如果调用对象包含值,返回该值,否则返回s 获取值
 * map(Function f) : 如果有值对其处理,并返回处理后的Optional,否则放回Optional.empty()
 * flatMap(Function mapper) : 与map 类似, 要求返回值必须是Optional
 */
public class OptionalDemo {

    /**
     * 快速定位空指针异常
     */
    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(null);
        Employee emp = op.get();

    }

    @Test
    public void test2(){
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());

        Optional<Object> op2 = Optional.ofNullable(null);
        System.out.println(op2);
    }

    @Test
    public void test3(){
        Optional<Employee> o = Optional.ofNullable(new Employee("lisi"));
        Employee emp = o.orElse(new Employee("zhangsan", 22, 3000, Employee.Status.FREE));
        System.out.println(emp);

        //可以通过函数式接口传入任何你想要的功能
        Optional<Employee> o1 = Optional.ofNullable(null);
        Employee employee = o1.orElseGet(() -> new Employee());
        System.out.println(employee);
    }

    /**
     * map
     * flatmap:就是参数必须为Optional和,stream里面的差不多作用
     */
    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee("zhansan", 22, 10000, Employee.Status.FREE));

        Optional<String> name = op.map(Employee::getName);
        System.out.println(name);

    }
}
