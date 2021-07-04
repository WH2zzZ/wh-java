package com.wanghan.java8.jvm.heap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * String table垃圾回收
 * -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 * -XX:StringTableSize=1009 设置串池的桶的个数
 * @Author WangHan
 * @Create 2020/5/30 6:02 下午
 */
public class StringTable {

    /**
     * StringTable statistics:
     * Number of buckets       :     60013 =    480104 bytes, avg   8.000 类似hashtable的实现。数组+链表，桶的个数是这么多
     * Number of entries       :       830 =     19920 bytes, avg  24.000 存储了830个String对象
     * Number of literals      :       830 =     56288 bytes, avg  67.817 字符串常量的个数
     * Total footprint         :           =    556312 bytes
     * Average bucket size     :     0.014
     * Variance of bucket size :     0.014
     * Std. dev. of bucket size:     0.118
     * Maximum bucket size     :         2
     * @param args
     */
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        List<String> contain = new ArrayList<>();
        System.in.read();
        int i = 0;
        try {
            for (int i1 = 0; i1 < 10000000; i1++) {
                //而放到堆中则不会太影响运行时间，因为占用的是堆内存空间
                String s = String.valueOf(i1%10);
                /*
                 * 发现多次GC会严重影响该程序的时间
                 * -XX:StringTableSize=4000000可以设置桶的个数，这里设置大一点看一下是否会加快程序速度
                 * 可以设置成最大字符串数量的1/5～1/2
                 */
//                s.intern();
                contain.add(s.intern());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }
        System.in.read();
    }
}
