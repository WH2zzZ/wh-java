package leetcode.kmp;

class Solution {

    public static void main(String[] args) {
        System.out.println(kmp("abcabcsabc", "abcs"));
    }

    // KMP算法，返回匹配的首字符下标，否则返回-1
    public static int kmp(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNext(s2);

        int i1 = 0;
        int i2 = 0;

        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
                // next数组在i2这个位置的最大匹配前后缀为-1， 则i1跳到下一位元素匹配
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        if (i2 >= str2.length) {
            return i1 - str2.length;
        } else {
            return -1;
        }
    }

    // 计算next数组
    public static int[] getNext(String t) {
        char[] str = t.toCharArray();
        int length = t.length();
        int[] next = new int[length];
        next[0] = -1;
        next[1] = 0;
        // 哪个位置的字符和 i - 1 这个位置比, 最开始和1这个位置比
        int cn = next[1];
        // next数组开始计算的位置
        int i = 2;
        while (i < next.length) {
            if (str[i - 1] == str[cn]) {
                next[i] = cn + 1;
                i++;
                cn = next[i];
            } else if(cn > 0) {
                cn = next[cn];
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
    }
}
