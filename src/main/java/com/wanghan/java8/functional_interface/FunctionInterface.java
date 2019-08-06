package com.wanghan.java8.functional_interface;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大核心函数式接口
 *
 * Consumer<T> : 消费型接口 void accept(T t);
 *
 * Supplier<T> : 供给型接口 void get(T t);
 *
 * Function<T, R> : 函数型接口 R apply(T t);
 *
 * Predicate<T> : 断言型接口 boolean test(T t);
 */
public class FunctionInterface {


    //消费型接口
    @Test
    public void testConsumer(){
        this.consumer("消费good", (s) -> System.out.println(s));
    }

    public void consumer(String good, Consumer<String> consumer){
        consumer.accept(good);
    }

    //供给型接口
    @Test
    public void testSupplier(){
        Integer num = 10;
        List<Integer> newNum = this.supply(num, () -> (int)(Math.random() * 100));
        newNum.forEach(System.out::println);
    }

    //返回num个随机数
    public List<Integer> supply(Integer num, Supplier<Integer> supplier){
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < num; i++){
            Integer x = supplier.get();
            nums.add(x);
        }
        return nums;
    }

    //函数型接口
    @Test
    public void testFunction(){
        Integer num = 10;
        String s = this.function(num, n -> num + "wh use function");
        System.out.println(s);
    }

    public String function(Integer num, Function<Integer, String> function){
        return function.apply(num);
    }

    //断言型接口
    @Test
    public void testPredicate(){
        List<String> list = Arrays.asList("zhangsan", "lisi", "zhouwu", "zhaoliu", "qian");
        List<String> newList = this.filterString(list, x -> x.length() < 5);
        newList.forEach(s -> System.out.println(s));
    }
    //将List集合中的字符串过滤
    public List<String> filterString(List<String> list, Predicate<String> pre){
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)){
                newList.add(s);
            }
        }
        return newList;
    }
}
