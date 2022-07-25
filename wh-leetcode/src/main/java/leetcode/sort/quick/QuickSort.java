package leetcode.sort.quick;

import java.util.Arrays;

/**
 * 快速排序
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 算法步骤
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 步骤1：从数列中挑出一个元素，称为 “基准”（pivot）；
 * 步骤2：重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 步骤3：递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * @Author WangHan
 * @Create 2021/4/17 9:17 下午
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 0, 1, 2, 3, 4, 11, 10, 20, 17};
//        int[] arr = new int[]{5,1,1,2,0,0};
        long startTime = System.currentTimeMillis();

        //选择排序
        quickSort(arr, 0, arr.length - 1);

        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println("speed time ms :" + (endTime - startTime));
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = partition(nums, start, end);

        //对左边快排
        //退出递归条件
        if (middle - start > 1) {
            quickSort(nums, start, middle - 1);
        }
        //对右边快排
        //如果中间值和要判断的最后一个之间只差一个元素 甚至是等于 那么就需要退出递归
        if (end - middle > 1) {
            quickSort(nums, middle + 1, end);
        }
    }

    private static int partition(int[] nums, int start, int end) {

        int middle = start;

        int pivot = nums[middle];
        start = middle + 1;

        while (start < end) {
            while (start < end && nums[start] < pivot) {
                start++;
            }
            while (start < end && nums[end] > pivot) {
                end--;
            }
            if (start < end) {
                swap(nums, start, end);
            }
        }

        if (nums[end] > pivot) {
            end--;
        }
        swap(nums, middle, end);
        System.out.println(Arrays.toString(nums));
        return end;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
