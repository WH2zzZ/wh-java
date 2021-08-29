package com.wanghan.java8.feature.stream.principle;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Author WangHan
 * @Create 2021/7/4 4:31 下午
 */
public class Demo {

    @Test
    public void test1() {

        Arrays.asList(1,2,3,4)
                .stream()
                .peek(integer -> System.out.println(integer))
                .sorted()
                .forEach(integer -> System.out.println(integer));
        IntStream.range(1, 10)
                .peek(value -> System.out.println("Step.1 -> " + value))
                .limit(3)
                .peek(value -> System.out.println("Step.2 -> " + value))
                .forEach(value -> System.out.println("Step.3 -> " + value));
    }

    @Test
    public void test2() {
        IntStream.range(1, 10)
                .peek(value -> System.out.println("Step.1 -> " + value))
                .skip(4)
                .peek(value -> System.out.println("Step.2 -> " + value))
                .forEach(value -> System.out.println("Step.3 -> " + value));
    }
}
