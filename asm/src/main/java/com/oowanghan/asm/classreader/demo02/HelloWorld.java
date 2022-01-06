package com.oowanghan.asm.classreader.demo02;


/**
 * 预期目标
 * public class HelloWorld {
 *     public int test(String name, int age, long idCard, Object obj) {
 *         System.out.println(name);
 *         System.out.println(age);
 *         System.out.println(idCard);
 *         System.out.println(obj);
 *         int hashCode = 0;
 *         hashCode += name.hashCode();
 *         hashCode += age;
 *         hashCode += (int) (idCard % Integer.MAX_VALUE);
 *         hashCode += obj.hashCode();
 *         System.out.println(hashCode);
 *         return hashCode;
 *     }
 * }
 */
public class HelloWorld {
    public int test(String name, int age, long idCard, Object obj) {
        int hashCode = 0;
        hashCode += name.hashCode();
        hashCode += age;
        hashCode += (int) (idCard % Integer.MAX_VALUE);
        hashCode += obj.hashCode();
        return hashCode;
    }
}