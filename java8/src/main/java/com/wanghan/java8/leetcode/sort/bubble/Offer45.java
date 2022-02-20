package com.wanghan.java8.leetcode.sort.bubble;

/**
 * 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0

 * @Author WangHan
 * @Create 2021/4/16 9:36 上午
 */
public class Offer45 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        System.out.println(minNumber(nums));
    }

    public static String minNumber(int[] nums) {
        if(nums.length == 1){
            return nums[0] + "";
        }

        for (int i = 0; i < nums.length - 1; i++){
            for (int j = i + 1; j > 0; j--){
                if (("" + nums[j - 1] + nums[j]).compareTo("" + nums[j] + nums[j - 1]) > 0){
                    int temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                }else {
                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int num : nums) {
            result.append(num);
        }
        return result.toString();
    }
}
