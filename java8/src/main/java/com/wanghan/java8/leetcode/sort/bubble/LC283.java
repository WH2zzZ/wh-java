package com.wanghan.java8.cleancode.sort.bubble;

import com.wanghan.java8.cleancode.util.ArrayUtils;

import java.util.Arrays;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @Author WangHan
 * @Create 2021/4/16 11:24 上午
 */
public class LC283 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZeroes(new int[]{0, 0})));
    }


    /**
     * 我们创建两个指针i和j，第一次遍历的时候指针j用来记录当前有多少非0元素。即遍历的时候每遇到一个非0元素就将其往数组左边挪，第一次遍历完后，j指针的下标就指向了最后一个非0元素下标。
     * 第二次遍历的时候，起始位置就从j开始到结束，将剩下的这段区域内的元素全部置为0
     *
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        if (nums == null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for (int i = j; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    /**
     * 自己写的傻逼算法 O(n^2)
     * 双层循环
     *
     * @param nums
     * @return
     */
    public static int[] moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }

        //记录移动0的位置，比如第一个0移动到最后， 下一个0放到这个前面即可
        int zeroIndex = 0;
        for (int i = 0; i < nums.length - zeroIndex; i++) {
            //可能都是0导致索引越界， 所以要判断zeroIndex不等于数组长度
            while (nums[i] == 0 && nums.length != zeroIndex) {
                //这里j的限制就是将数组长度减去移动0位置的前面即可
                for (int j = i + 1; j < nums.length - zeroIndex; j++) {
                    int temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                }
                //每次移动完，末尾0的数量要记住
                zeroIndex++;
            }
        }

        return nums;
    }

}
