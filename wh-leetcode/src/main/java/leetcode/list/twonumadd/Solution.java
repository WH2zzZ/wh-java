package leetcode.list.twonumadd;

class Solution {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int firstSum = l1.val + l2.val;
        ListNode result = new ListNode(firstSum % 10);

        ListNode tempNode = result;
        int temp = firstSum / 10;
        while (l1.next != null || l2.next != null || temp != 0) {
            int sum = temp;
            if (l1.next != null) {
                sum += l1.next.val;
                l1 = l1.next;
            }

            if (l2.next != null) {
                sum += l2.next.val;
                l2 = l2.next;
            }

            int val = sum % 10;
            temp = sum / 10;

            tempNode.next = new ListNode(val);
            tempNode = tempNode.next;
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}