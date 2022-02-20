package com.wanghan.java8.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author WangHan
 * @Create 2020/5/19 1:43 下午
 */
class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        HashMap<Integer, Integer> map = new HashMap(64);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                Integer integer = map.get(target - nums[i]);
                result[0] = i;
                result[1] = integer;
                return result;
            }
        }
        return null;
    }

    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int mx = max;
            int mn = min;
            max = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            min = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(max, ans);
        }
        return ans;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length)
            return new int[0];


        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> q = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
            if (q.peekFirst() == (i - k)) {
                q.pollFirst();
            }
            if (i >= (k - 1)) {
                res[index++] = nums[q.peekFirst()];
            }
        }
        return res;
    }

    static int delCount = 0;

    public static boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                if (delCount == 0) {
                    delCount++;
                    return validPalindrome(s.substring(i, j)) || validPalindrome(s.substring(i + 1, j + 1));
                }
                return false;
            }
        }
        return true;
    }

    public static int findTheLongestSubstring(String s) {
        byte[] record = new byte[5];
        int[] index = new int[5];
        Arrays.fill(index, -1);

        for (int i = 0; i < s.length(); i++) {

        }
        return 0;
    }

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

    public int numberOfSubarrays(int[] nums, int k) {
        return 0;
    }

    public static void main(String[] args) {
//        int[] ints = twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
//        System.out.println(ints);
//        maxProduct(new int[]{2,3,-2,4,-6});
//        maxSlidingWindow(new int[]{2,3,-2,4,-6}, 3);
//        System.out.println(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
//        System.out.println(findTheLongestSubstring("aaisidkwlaksfee"));
//        System.out.println(1 << 5);
        System.out.println(subarraySum(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
    }
}
