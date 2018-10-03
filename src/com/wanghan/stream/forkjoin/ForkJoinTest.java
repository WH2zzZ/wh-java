package com.wanghan.stream.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

    /**
     * ForkJoin框架
     */
    @Test
    public void test1(){
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 10000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());
    }

    /**
     * 普通for
     */
    @Test
    public void test2(){
        Instant start = Instant.now();

        Long sum = 0L;
        for (long i = 0; i <= 10000000000L; i++){
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }

    /**
     * java8的并行流
     * 只要加上parallel方法,就变成了并行流
     */
    @Test
    public void test3(){
        LongStream.rangeClosed(0, 100000000000L).parallel().reduce(0, Long::sum);
    }

}
