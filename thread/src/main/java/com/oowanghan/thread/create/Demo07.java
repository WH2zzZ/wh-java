package com.oowanghan.thread.create;

import java.util.Arrays;
import java.util.List;

/**
 * lambada表达式（springboot的async注解，也是一种创建线程的方式）
 */
public class Demo07 {

    public int add(List<Integer> values){
        values.stream().forEach(System.out::println);
        System.out.println();
        //并行流
        //打印顺序并不会按照塞入顺序
        values.parallelStream().forEach(System.out::println);
        return values.stream().mapToInt(value -> value).sum();
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(10,20,30,40);

        int result = new Demo07().add(values);
        System.out.println(result);
    }
}
