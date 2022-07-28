package leetcode.str._1331;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1331. 数组序号转换
 * https://leetcode.cn/problems/rank-transform-of-an-array/
 */
class Solution {


    public int[] arrayRankTransform(int[] arr) {

        final HashMap<Integer, Integer> map = new HashMap<>((int) (arr.length / 0.75));

        int[] resultArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);

        int currentIndex = 1;
        for (int i = 0; i < arr.length; i++) {
            Integer index = map.get(arr[i]);
            if (index == null) {
                map.put(arr[i], currentIndex);
                currentIndex++;
            }
        }

        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = map.get(resultArr[i]);
        }
        return resultArr;

    }

    public static void main(String[] args) {
        final int[] ints = new Solution().arrayRankTransform(new int[]{40, 10, 20, 30, 10});
        System.out.println(Arrays.toString(ints));
    }
}