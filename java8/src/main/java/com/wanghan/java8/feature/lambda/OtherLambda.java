package com.wanghan.java8.feature.lambda;


import com.wanghan.java8.spring.ioc.demo01.A;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * 初试Lambda表达式
 */
public class OtherLambda {

    @Test
    public void test01(){
        //级联表达式
        Function<Integer, Function<Integer, Integer>> function = x ->y -> x + y;
        //柯里化：把多个参数的函数转换为只有一个参数的函数
        //柯里化的目的：函数标准化
        //高阶函数：就是返回函数的函数
        //函数式编程概念
        System.out.println(function.apply(1).apply(2));

        int[] nums = {1,2};
        Function f = function;
        for (int i = 0; i < nums.length; i++) {
            Object apply = f.apply(nums[i]);
            if (apply instanceof Function){
                continue;
           }else {
                System.out.println(apply);
            }
        }
    }
}
