package com.wanghan.lambda;


import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 1.Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 成为箭头操作符或Lambda操作符
 *                          箭头操作符，将Lambda拆分成两部分：
 *                              左侧：Lambda表达式的参数列表
 *                              右侧：Lambda表达式所需要执行的功能，即Lambda体
 *
 * 2.语法格式一：无参数，无返回值
 *      () -> System.out.println("Hello Lambda")
 *
 * 3.语法格式二：一个参数，无返回值
 *      (p) -> System.out.println("Hello Lambda")  ------若左侧只有一个参数,小括号可以不写
 *
 * 4.语法格式三：有两个参数，Lambda体中有多条语句,有返回值
 *      (p1, p2) -> {System.out.println("Hello Lambda")}
 *
 * 5.语法格式四: 有两个参数，Lambda体中有一条语句,有返回值,大括号 和 return 都可以不写
 *      (p1, p2) -> System.out.println("Hello Lambda")
 *
 * Lambda参数列表的数据类型可以省略不写,因为JVM编译器可以通过上下文推断出数据类型
 *
 * Lambda需要"函数式接口的支持",即:接口只有一个抽象方法的的接口,称为函数式接口.可以使用注解: @FunctionalInterface修饰
 *                               可以检查是否是函数式接口
 */
public class ComplexLambda {

    /**
     * 语法格式一
     */
    @Test
    public void demo01(){
        int num = 1;

        //最初
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda" + num);
            }
        };
        r.run();
        System.out.println("------------------------------");

        //Lambda表达式(注意:使用匿名内部类的时候,里面的参数同样为final)
        Runnable lambdaR1 = () -> System.out.print("Hello Lambda" + num);
        lambdaR1.run();
    }

    /**
     * 语法格式二
     */
    @Test
    public void demo02(){
        Consumer<String> con1 = (x) -> System.out.println(x);
        con1.accept("Hello Lambda");

        Consumer<String> con2 = x -> System.out.println(x);
        con2.accept("Hello Lambda");
    }

    /**
     * 语法格式三
     */
    @Test
    public void demo03(){
        Comparator<Integer> com1 = (x, y) -> {
            System.out.println("Hello Lambda");
            return Integer.compare(x, y);
        };

        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
    }
}
