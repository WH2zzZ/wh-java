package leetcode.slidingWindow;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/online-stock-span/
 */
class StockSpanner {

    /**
     * 存放每日的股价栈
     */
    private final Stack<Integer> priceStack;
    /**
     * 存放比今日之前小的连续天数
     */
    private final Stack<Integer> countStack;

    public StockSpanner() {
        priceStack = new Stack<>();
        countStack = new Stack<>();
    }
    
    public int next(int price) {
        int count = 1;

        while (!priceStack.isEmpty() && priceStack.peek() <= price) {
            priceStack.pop();
            count += countStack.pop();
        }
        priceStack.push(price);
        countStack.push(count);
        return count;
    }

}