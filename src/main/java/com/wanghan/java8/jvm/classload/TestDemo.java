package com.wanghan.java8.jvm.classload;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 主动使用：会初始化class，
 * 被动使用：不会初始化class
 *
 * @Author WangHan
 * @Create 2020/4/5 12:56 下午
 */
public class TestDemo {
    /**
     * javap -c
     */
    @Test
    public void test1() {
        new Son(); //主动引用
    }

    @Test
    public void test2(){
        System.out.println(Son.a); //在引用一个静态字段时，只有直接定义该字段的类才会被初始化,-XX:+TraceClassLoading
    }

    @Test
    public void test3(){
        //在引用final常量时，不会加载，初始化任何class，因为常量在编译阶段就会存入到调用这个常量的方法所在的类的常量池中，
        System.out.println(Son.b);
        //也就是说，Son.b的值已经在Test类的常量池了
        //ldc：将string类型的常量值从常量池推送至栈顶
        //bipush：将单字节（-128-127）的常量推送至栈顶
        //sipush：将一个短整型常量值（-3278-32767）推送至栈顶
        //iconst_1：将int类型1推送至栈顶，最多就到iconst_5，最小到iconst_m1(-1)
        //这个例子，会初始化son是因为，只有在运行期才能知道son的常量值，所以在编译期常量不会存入到调用该常量的方法所在的类的常量池中
        System.out.println(Son.RANDOM);
    }

    /**
     *  0: iconst_1
     *  1: anewarray     #2                  // class com/wanghan/java8/jvm/classload/Son
     *  4: astore_1
     *  5: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *  8: aload_1
     *  9: invokevirtual #9                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
     *  12: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
     *  15: iconst_1
     *  16: newarray       int
     */
    @Test
    public void test4(){
//        Son son = new Son();
        Son[] sons = new Son[1];//不会初始化son
        System.out.println(sons.getClass());//class [Lcom.wanghan.java8.jvm.classload.Son; jvm虚拟机在运行期动态加载出来的
        int[] num = new int[1];
    }

    /**
     * 当一个接口在初始化的时候，并不要求其父接口都完成初始化
     * 如果运行期才会生成的常量值，那么需要加载该class文件
     */
    @Test
    public void test5(){
        System.out.println(ISon.a);
    }

    /**
     * 更换public static int b = 0;位置放到new Singleton之后，b在打印出来的值变成了0
     * 说明类在初始化的时候是顺序加载的，在初始化的时候Singleton先赋值为new Singleton()，这个时候构造函数给a，b加1
     * 然后再给b赋值0，这个时候b的值就是0了
     */
    @Test
    public void test6(){
        Singleton singleton = Singleton.getInstance();
        System.out.println(Singleton.a);
        //如果b静态变量放到了构造方法的下面，那么b打印的值是0！
        System.out.println(Singleton.b);
    }

    @Test
    public void test10() throws ClassNotFoundException {
        //下面是加载class文件
        Class parentClass = Class.forName("com.wanghan.java8.jvm.classload.Parent");
        Method[] methods = parentClass.getDeclaredMethods();
        for (Method method : methods) {
            String mod = Modifier.toString(method.getModifiers());//访问标示符转换成可读字符串
            System.out.println("===========Start============");
            System.out.println("methodName:"+method.getName());
            System.out.println("modInt:"+method.getModifiers());
            System.out.println("mod:"+mod);
            Class<?>[] types = method.getParameterTypes();
            System.out.println("types:"+ Arrays.toString(types));
            System.out.println("===========End============");
        }
    }

}
