package leetcode.str._03;

import java.util.HashMap;

class Solution {


    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        String[] arr = new String[s.length()];
        arr[0] = s.substring(0, 1);


        for (int i = 1; i < s.length(); i++) {
            String currentElement = s.substring(i, i + 1);
            if (arr[i - 1].contains(currentElement)) {
                int indexOf = arr[i - 1].indexOf(currentElement);
                arr[i] = arr[i - 1].substring(indexOf + 1) + currentElement;
            } else {
                arr[i] = arr[i - 1] + currentElement;
            }
        }

        int max = arr[0].length();
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i].length()) {
                max = arr[i].length();
            }
        }
        return max;
    }

    public int method2(String s) {
        if (s.length() == 0) return 0;
        // 记录每个元素最近一次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        // 窗口头指针
        int start = 0;
        // end 窗口尾指针
        for (int end = 0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)) + 1);
            }
            map.put(s.charAt(end), end);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String dvdf = "d";
        System.out.println(dvdf.indexOf("d"));
        System.out.println(dvdf.substring(1));
        System.out.println(solution.lengthOfLongestSubstring(dvdf));
    }
}