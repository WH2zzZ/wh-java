package com.wanghan.java8.cleancode.sort.insert;

import com.wanghan.java8.cleancode.util.ArrayUtils;

import java.util.Arrays;

/**
 * 插入排序
 * 是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，
 * 需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 * <p>
 * 算法步骤
 * 步骤1: 从第一个元素开始，该元素可以认为已经被排序；
 * 步骤2: 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 步骤3: 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 步骤4: 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 步骤5: 将新元素插入到该位置后；
 * 步骤6: 重复步骤2~5。
 * <p>
 * 算法分析
 * 最佳情况：T(n) = O(n)
 * 最坏情况：T(n) = O(n2)
 * 平均情况：T(n) = O(n2)
 *
 * @Author WangHan
 * @Create 2021/4/15 10:28 上午
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 0, 1, 2, 3, 4, 10, 11, 17, 20};
        long startTime = System.currentTimeMillis();

        //选择排序
        insertionSort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println("speed time ms :" + (endTime - startTime));
    }

    /**
     * 插入排序-移动法
     *
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {

            //当前比较到的有序队列的位置
            int currentCompareIndex = i;
            //需要比较的元素
            int current = array[i + 1];

            while (currentCompareIndex >= 0 && array[currentCompareIndex] > current) {
                array[currentCompareIndex + 1] = array[currentCompareIndex];
                currentCompareIndex--;
            }

            array[++currentCompareIndex] = current;
            ArrayUtils.print(array);
        }
        return array;
    }

    /**
     * 插入排序-交换法
     * @param array
     * @return
     */
    public static int[] insert(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {

            int j = i;

            while (j >= 1 && array[j - 1] > array[j]) {
                int temp = array[j - 1];
                array[j - 1] = array[j];
                array[j] = temp;
                j--;
            }

            ArrayUtils.print(array);
        }
        return array;
    }

}
