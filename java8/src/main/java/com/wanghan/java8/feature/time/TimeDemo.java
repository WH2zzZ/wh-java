package com.wanghan.java8.feature.time;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 曾经的时间API会有线程安全问题
 */
public class TimeDemo {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //线程不安全
                //return dateFormat.parse("20181005");
                //线程安全
                return DateFormateThreadLocal.convert("20181005");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            results.add(pool.submit(task));
        }

        for (Future<Date> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();
    }

    /**
     * 1.8的解决方案,使用LocalDate
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = () -> LocalDate.parse("20181005", formatter);

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();
    }
}
