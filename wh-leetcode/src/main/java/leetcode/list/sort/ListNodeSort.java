package leetcode.list.sort;

/**
 * @Author WangHan
 * @Create 2022/5/22 3:59 下午
 */
public class ListNodeSort {

    public static void main(String[] args) {
        ListNodeSort listNodeSort = new ListNodeSort();

        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(7, node2);
        ListNode node4 = new ListNode(6, node3);

        ListNode listNode = listNodeSort.sortList(node4);
        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 找中间点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) {
                slow = slow.next;
            }
        }

        // 分割链表
        ListNode right = mergeSort(slow.next);
        slow.next = null;
        ListNode left = mergeSort(head);

        // 合并左右链表
        return mergeList(right, left);
    }

    private ListNode mergeList(ListNode right, ListNode left) {
        ListNode newList = new ListNode(0);
        ListNode head = newList;
        while (right != null && left != null) {
            if (right.val < left.val) {
                newList.next = right;
                right = right.next;
            }else {
                newList.next = left;
                left = left.next;
            }
            newList = newList.next;
        }
        // 此时左链表 和 右链表 肯定存在一个没有合并完全
        if (left != null) {
            newList.next = left;
        }else {
            newList.next = right;
        }
        return head.next;
    }
}
