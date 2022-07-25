package leetcode.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @Author WangHan
 * @Create 2022/7/19 00:49
 */
public class TwoSum {

    public static void main(String[] args) {

        int[] param = {2, 7, 11, 15};
        int[] twoSum = new Solution().twoSum(param, 9);
        System.out.println(Arrays.toString(twoSum));
    }
}


class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}