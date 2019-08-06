package com.wanghan.java8.lambda;


import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 初试Lambda表达式
 */
public class SimpleLambda {

    @Test
    public void test01(){
        //回忆匿名内部类
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //真正有用的代码
                return Integer.compare(o1, o2);
            }
        };
        //使用Lambda表达式
        Comparator<Integer> comLamdba = (o1, o2) -> Integer.compare(o1, o2);


        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        TreeSet<Integer> treeSetLambda = new TreeSet<>(comLamdba);

    }
}
