package com.wanghan.java8.leetcode.stack;

import java.util.LinkedList;

/**
 *
 * @Author WangHan
 * @Create 2020/5/19 1:43 下午
 */class Solution {

    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 示例:
     *
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     *
     */
    public static String decodeString(String s) {
        LinkedList<String> list = new LinkedList<>();
        int index = 0;
        //0:表示上一个字符类型为其他，1：表示上一个字符类型为数字，2：表示上一个字符类型为字母
        int type = 0;
        String result = "";
        while (index < s.length()){
            Character c = s.charAt(index);
            index++;
            if (Character.isDigit(c)){
                if (type == 1){
                    String last = list.removeLast();
                    last = last + c;
                    list.addLast(last);
                }else {
                    list.addLast(String.valueOf(c));
                }
                type = 1;
                continue;
            }
            if (Character.isLetter(c)){
                if (type == 2){
                    String last = list.removeLast();
                    last = last + c;
                    list.addLast(last);
                }else {
                    list.addLast(String.valueOf(c));
                }
                type = 2;
                continue;
            }
            if ("[".equals(String.valueOf(c))){
                type = 0;
                continue;
            }
            if ("]".equals(String.valueOf(c))){
                type = 0;
                String last = list.removeLast();
                String record = "";
                Integer count = Integer.valueOf(list.removeLast());
                while (count > 0) {
                    count--;
                    record += last;
                }
                if (list.size() != 0){
                    type = 2;
                    if (isNumeric(list.getLast())){
                       list.addLast(record);
                    }else {
                        String s1 = list.removeLast() + record;
                        list.addLast(s1);
                    }
                }else {
                    result += record;
                }
                continue;
            }
        }
        while (list.size() > 0){
            result += list.removeLast();
        }
        return result;
    }
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }
}
