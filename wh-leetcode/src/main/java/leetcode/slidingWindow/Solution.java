package leetcode.slidingWindow;

/**
 * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 */
class Solution {

    /**
     * 普通解法， 核心在于如何在每次滑动窗口变动时，将O(K)复杂度变为O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] array = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int result = nums[i];
            if ((i + k) > nums.length) {
                break;
            }
            for (int j = 1; j < k; j++) {
                result = Math.max(result, nums[i + j]);
            }
            array[i] = result;
        }
        return array;
    }

    /**
     * 滑动窗口 通过双端队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        MyDeque deque = new MyDeque();
        // 初始化双端队列
        for (int i = 0; i < k; i++) {
            pushElement(deque, nums[i]);
        }
        result[0] = deque.peekFirst();

        // 对双端队列计算
        for (int i = k; i < nums.length; i++) {
            // 左指针：i-k  开始移动，比较双端队列头指针是否是左指针移动后的过期元素
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            // 添加右指针:i对应元素到双端队列
            pushElement(deque, nums[i]);
            // 双端队列第一个元素就是最大值
            result[i - k + 1] = deque.peekFirst();
        }
        return result;
    }

    private void pushElement(MyDeque deque, int num) {
        // 判断比当前数字小的，都推出
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.removeLast();
        }
        deque.addLast(num);
    }

    public class MyDeque {

        private Node first;

        private Node last;

        void linkLast(int e) {
            final Node l = last;
            final Node newNode = new Node(l, e, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
        }

        public int removeFirst() {
            final Node f = first;
            return unlinkFirst(f);
        }

        private int unlinkFirst(Node f) {
            final int element = f.item;
            final Node next = f.next;
            first = next;
            if (next == null)
                last = null;
            else
                next.prev = null;
            return element;
        }

        public int removeLast() {
            final Node l = last;
            return unlinkLast(l);
        }

        private int unlinkLast(Node l) {
            final int element = l.item;
            final Node prev = l.prev;
            last = prev;
            if (prev == null)
                first = null;
            else
                prev.next = null;
            return element;
        }

        public void addLast(int e) {
            linkLast(e);
        }

        public int peekFirst() {
            final Node f = first;
            return (f == null) ? null : f.item;
        }

        public int peekLast() {
            final Node l = last;
            return (l == null) ? null : l.item;
        }

        public boolean isEmpty() {
            return first == null;
        }

        private class Node {
            int item;
            Node next;
            Node prev;

            Node(Node prev, int element, Node next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }
    }

}