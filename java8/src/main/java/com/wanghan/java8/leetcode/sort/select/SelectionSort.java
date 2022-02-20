package com.wanghan.java8.leetcode.sort.select;

import com.wanghan.java8.leetcode.util.ArrayUtils;

import java.util.Arrays;

/**
 * 选择排序
 *      选择排序 是表现最稳定的排序算法之一 ，因为无论什么数据进去都是O(n2)的时间复杂度 ，所以用到它的时候，数据规模越小越好。
 *      唯一的好处可能就是不占用额外的内存空间了吧。
 *
 * 算法步骤
 *      步骤1：初始状态：无序区为R[1…n]，有序区为空；
 *      步骤2：第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1…i-1]和R(i…n）。
 *            该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
 *            使R[1…i]和R[i+1…n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区
 *      步骤3：n-1趟结束，数组有序化了。
 *
 * 算法分析
 *      最佳情况：T(n) = O(n2)
 *      最差情况：T(n) = O(n2)
 *      平均情况：T(n) = O(n2)
 *
 * @Author WangHan
 * @Create 2021/4/15 10:28 上午
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 0, 1, 2, 3, 4, 10, 11, 17, 20};
        long startTime = System.currentTimeMillis();

        //选择排序
        selectionSort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println("speed time ms :" + (endTime - startTime));
    }

    /**
     * 选择排序
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            //存储最小索引
            int minIndex = i;
            for (int j = i + 1;  j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                     minIndex = j;
                }
            }
            if (minIndex != i){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
            ArrayUtils.print(array);
        }
        return array;
    }

}
