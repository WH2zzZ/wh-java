package com.wanghan.java8.stream;

import com.wanghan.java8.lambda.strategy_pattern.Employee;
import com.wanghan.time.HexTest;
import org.junit.jupiter.api.Test;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 中间操作
 * 注意:中间操作不做任何执行,只有碰到终止操作的时候才会一次性执行全部内容. 即"惰性求值"
 * 筛选与切片
 * filter -- 接受Lambda, 从流中排除某些元素
 * limit -- 截断流,使其元素不超过给定数量
 * skip(n) -- 跳过元素,放回一个扔掉前 n 个元素的流. 若流中元素不足 n 个,则返回一个空流.与limit(n)互补
 * distinct -- 筛选,通过流所生成元素的hashCode() 和 equals() 去去除重复元素
 */
public class StreamMidOperation1 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 20, 2000),
            new Employee("zhangsan", 21, 3000),
            new Employee("zhangsan", 22, 4000),
            new Employee("zhangsan", 23, 5000),
            new Employee("zhangsan", 24, 6000),
            new Employee("zhangsan", 24, 6000),
            new Employee("zhangsan", 24, 6000)
    );

    /**
     * filter -- 接受Lambda, 从流中排除某些元素.
     * 内部迭代 :会打印多次输出语句
     */
    @Test
    public void test1(){
        Stream<Employee> employeeStream = employees.stream().filter(e -> {
            System.out.println("Stream API的中间操作执行了");
            return e.getAge() <= 23;
        });

        //终止操作(打上注释可以查看中间操作在终止操作之前是不会执行的)
        employeeStream.forEach(System.out::println);

        List<Employee> collect = employees.stream().filter(e -> {
            System.out.println("Stream API的中间操作执行了");
            return e.getAge() <= 23;
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test2(){
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * limit -- 截断流,使其元素不超过给定数量
     */
    @Test
    public void test3(){
        employees.stream()
                 .limit(3)
                 .forEach(System.out::println);
    }

    /**
     * skip(n) -- 跳过元素,放回一个扔掉前 n 个元素的流. 若流中元素不足 n 个,则返回一个空流.与limit(n)互补
     */
    @Test
    public void test4(){
        employees.stream()
                 .skip(2)
                 .forEach(System.out::println);
    }

    /**
     * distinct -- 筛选,通过流所生成元素的hashCode() 和 equals() 去去除重复元素(需要重写hashcode 和 equals方法)
     */
    @Test
    public void test5(){
        employees.stream()
                 .distinct()
                 .forEach(System.out::println);

    }

    @Test
    public void test6(){
        String first = "AA";

        String time = makeTime();

        String mobileNum = Long.toHexString(Long.parseLong("13013041809"));
        for (int i = 0; i < (10 - mobileNum.length()); i++) {
            mobileNum = "0" + mobileNum;
        }

        String result = first + time + mobileNum + "00000000000000";
        System.out.println(time + mobileNum);
        System.out.println((time + mobileNum).getBytes().length);
        System.out.println(result);
        System.out.println(result.getBytes().length);
    }

    @Test
    public void test7() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] password = HexTest.hexToByteArr("AA2101a80307a3429100000000000000");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(password));
        SecretKey secretKey = keyGenerator.generateKey();

        byte[] secretKeyEncoded = HexTest.hexToByteArr("76ea59b72d425c61f2b2684278924046");
        SecretKeySpec aes = new SecretKeySpec(secretKeyEncoded, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aes);
        byte[] result = cipher.doFinal(password);
        System.out.println(Arrays.toString(result));
        System.out.println(HexTest.byteArrToHex(result));
        System.out.println(Arrays.toString(HexTest.hexToByteArr("2f62fa821c87e8e32d3e64a90d4df24b")));
    }

    @Test
    public void test8() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] secretKeyEncoded = HexTest.hexToByteArr("7db238a923as393z");
        SecretKeySpec aes = new SecretKeySpec(secretKeyEncoded, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, aes);
        byte[] result = cipher.doFinal("password".getBytes());
        System.out.println(Arrays.toString(result));
        System.out.println(HexTest.byteArrToHex(result));
        System.out.println(Arrays.toString(HexTest.hexToByteArr("2f62fa821c87e8e32d3e64a90d4df24b")));
    }

    private String makeTime() {
        long startTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN)
                .toEpochSecond(ZoneOffset.of("+8"));
        long nowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String result = String.valueOf(Long.toHexString(nowTime - startTime));
        for (int i = 0; i < (6 - result.length()); i++) {
            result = "0" + result;
        }
        return result;
    }

    @Test
    public void test9(){
        System.out.println(Instant.now().getEpochSecond());
    }
}
