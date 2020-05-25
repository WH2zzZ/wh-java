package com.wanghan.java8.cleancode.prefix_method;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 前缀和
 * @Author WangHan
 * @Create 2020/5/19 1:43 下午
 */
class Solution {

    public static int findTheLongestSubstring(String s) {
        byte[] record = new byte[5];
        int[] index = new int[5];
        Arrays.fill(index, -1);

        for (int i = 0; i < s.length(); i++) {

        }
        return 0;
    }

    /**
     * 560.和为K的子数组
     */
    public static int subarraySum(int[] nums, int k) {
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int record = 0;
//            for (int j = i; j < nums.length; j++) {
//                record += nums[j];
//                if (record == k) {
//                    sum++;
//                }
//            }
//        }
//        return sum;

        int count = 0;
        int pre = 0;
        HashMap<Integer, Integer> map = new HashMap<>(32);
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;

    }

    /**
     * 1248.统计「优美子数组」
     */
    public static int numberOfSubArrays(int[] nums, int k) {
        // 数组 prefixCnt 的下标是前缀和（即当前奇数的个数），值是前缀和的个数(即出现这么多次前缀和的个数)。
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        // 遍历原数组，计算当前的前缀和，统计到 prefixCnt 数组中，
        // 并且在 res 中累加上与当前前缀和差值为 k 的前缀和的个数。
        int res = 0, count = 0;
        for (int num: nums) {
            count += num & 1;
            prefixCnt[count]++;
            if (count >= k) {
                res += prefixCnt[count - k];
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
//        System.out.println(findTheLongestSubstring("aaisidkwlaksfee"));
//        System.out.println(1 << 5);
//        System.out.println(subarraySum(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
        System.out.println(numberOfSubArrays(new int[]{2, 0, 1, 4, 3, 7, 4, 5, 3, 0}, 3));
    }
}
