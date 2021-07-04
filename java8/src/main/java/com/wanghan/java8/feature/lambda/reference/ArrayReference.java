package com.wanghan.java8.feature.lambda.reference;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * 数组引用
 *
 * 格式: Type :: new
 */
public class ArrayReference {

    @Test
    public void test01(){
        Function<Integer, String[]> function = x -> new String[x];
        String[] arr = function.apply(1);
        System.out.println(Arrays.toString(arr));

        Function<Integer, String[]> function1 = String[]::new;
        String[] arr1 = function1.apply(2);
        System.out.println(Arrays.toString(arr1));
    }
}
