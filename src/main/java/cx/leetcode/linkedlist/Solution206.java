package cx.leetcode.linkedlist;

public class Solution206 {
    public ListNode reverseList(ListNode head) {
        // 迭代
        // ListNode pre = null;
        // ListNode cur = head;
        // while (cur != null) {
        //     ListNode next = cur.next;
        //     cur.next = pre;
        //     pre = cur;
        //     cur = next;
        // }
        // return pre;

        // 递归
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static class ListNode {
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
