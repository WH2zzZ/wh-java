package leetcode.sort.bubble;

import leetcode.util.ArrayUtils;

import java.util.Arrays;

/**
 * 冒泡排序
 *      步骤1: 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 *      步骤2: 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 *      步骤3: 针对所有的元素重复以上的步骤，除了最后一个；
 *      步骤4: 重复步骤1~3，直到排序完成。
 *
 * 缺点及优势：
 *      因为冒泡排序必须要在最终位置找到之前不断交换数据项，所以它经常被认为是最低效的排序方法。这些 “浪费式” 的交换操作消耗了许多时间。
 *      但是，由于冒泡排序要遍历整个未排好的部分，它可以做一些大多数排序方法做不到的事。尤其是如果在整个排序过程中没有交换，我们就可断定列表已经排好。
 *      因此可改良冒泡排序，使其在已知列表排好的情况下提前结束。如果一个列表只需要几次遍历就可排好，
 *      冒泡排序就占有优势：它可以在发现列表已排好时立刻结束。
 *
 * 算法分析
 *      最佳情况：T(n) = O(n)
 *      最差情况：T(n) = O(n2)
 *      平均情况：T(n) = O(n2)
 *
 * @Author WangHan
 * @Create 2021/4/15 10:28 上午
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 0, 1, 2, 3, 4, 10, 11, 17, 4};
        long startTime = System.currentTimeMillis();

        //冒泡排序
//        bubbleSort(arr);
        //优化后的冒泡排序
        bubbleSort2(arr);

        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println("speed time ms :" + (endTime - startTime));
    }

    /**
     * 冒泡排序
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            ArrayUtils.print(array);
        }
        return array;
    }

    /**
     * 优化后的冒泡排序
     *
     * 如果没有交换就代表整个列表是有序的
     * @param array
     * @return
     */
    public static int[] bubbleSort2(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            boolean exchange = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    exchange = true;
                }
            }
            //如果没有交换可以直接退出了
            if (!exchange){
                return array;
            }
            ArrayUtils.print(array);
        }
        return array;
    }
}
