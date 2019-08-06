package com.wanghan.java8.lambda.reference;

import com.wanghan.java8.lambda.strategy_pattern.Employee;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用: 若Lambda 体中的内容有方法已经实现了,我们可以使用"方法引用"
 *          (可以理解为方法引用时方法体的另一种表现形式)
 *
 * 主要三种语法格式:
 *          对象 :: 实例方法名
 *          类 :: 静态方法名
 *          类 :: 实例方法名
 */
public class MethodReference {

    /**
     * 对象::实例方法名 :类调用实例方法,相当于想实现接口中的方法时,发现已经有类实现了相同的方法(传入参数和返回值均相同)
     *                  这个时候就使用这个实现了相同方法的类来完成接口中的方法实现
     */
    @Test
    public void test01(){
        //示例1
        Consumer<String> consumer1 = (x) -> System.out.println(x);
        consumer1.accept("low");

        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;
        consumer2.accept("nice");

        Consumer<String> consumer3 = System.out::println;
        consumer3.accept("very nice");

        //示例2
        Employee employee = new Employee("zhangsan", 20, 2000);
        String s = null;

        Supplier<String> supplier1 = () -> employee.getName();
        s = supplier1.get();
        System.out.println(s);

        Supplier<String> supplier2 = employee::getName;
        s = supplier2.get();
        System.out.println(s);
    }

    /**
     * 类 :: 静态方法名
     */
    @Test
    public void test02(){
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);

        Comparator<Integer> comparator2 = Integer::compareTo;
    }

    /**
     * 类 :: 实例方法名
     * 规则: 第一个参数为实例方法的调用者,第二个参数为实例方法的参数
     */
    @Test
    public void test03(){
        BiPredicate<String, String> predicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> predicate1 = String::equals;
    }
}
