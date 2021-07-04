package com.wanghan.java8.feature.stream;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author WangHan
 * @Create 2021/6/20 8:13 下午
 */
public class FirstStream {

    @Test
    public void test(){
        String str = "my name is 007";

        Stream.of(str.split(" ")).flatMap(s -> {
            //intStream/longStream 并不是Stream的子类， 所以要进行装箱 boxed
            return s.chars().boxed();
        }).forEach(integer -> {
            System.out.println((char)integer.intValue());
        });

        //peek用于debug，是个中间操作，和forEach是个final operation
        System.out.println("=====peek=====");
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::print);

        //可以保证并行流的输出是有序的
        Stream.of(str.split(" ")).parallel().forEachOrdered(s -> {
            System.out.println(s);
        });

        Optional<String> reduce = Stream.of(str.split(" ")).reduce((s, s2) -> s + "|" + s2);
        System.out.println(reduce.orElse(""));
    }

    @Test
    public void test2(){
        // 使用的默认线程池是 ForkJoinPool 线程池数量是 当前CPU的核数
        IntStream.range(1, 100)
                //多次调用 parallel/sequential，以最后一次为准
                .parallel().peek(this::print)
                .sequential().peek(this::print)
                .count();
    }

    private void print(int value) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }
}
