package com.wanghan.stream;

import com.wanghan.lambda.strategy_pattern.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一. Stream的三个操作步骤:
 *
 *  1. 创建Stream
 *
 *  2. 中间操作
 *
 *  3. 终止操作(终端操作)
 */
public class StreamInitOperation {

    //创建Stream(四种方式)
    @Test
    public void test1(){
        //1.可以通过Collection系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Arrays.stream(employees);

        //3.通过Stream静态方法of()
        Stream<String> stream1 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        //中间操作
        Stream<Integer> limit = iterate.limit(10);
        //终止操作
        limit.forEach(System.out::println);

        //生成
        Stream<Double> generate = Stream.generate(() -> Math.random());
        //中间操作
        Stream<Double> limit1 = generate.limit(10);
        //终止操作
        limit1.forEach(System.out::println);
    }


}
