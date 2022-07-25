package leetcode.slidingWindow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Author WangHan
 * @Create 2022/5/14 10:39 下午
 */
public class RemoveK {

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
    }

    public static String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !stack.isEmpty() && stack.getLast() > num.charAt(i)) {
                stack.removeLast();
                k--;
            }
            stack.addLast(num.charAt(i));
        }

        // 对k做剩余数值补偿
        while (k > 0) {
            stack.removeLast();
            k--;
        }

        while (!stack.isEmpty() && stack.getFirst() == '0') {
            stack.removeFirst();
        }
        if (stack.isEmpty()) {
            return "0";
        }
        return stack.stream().map(Objects::toString).collect(Collectors.joining());
    }
}
