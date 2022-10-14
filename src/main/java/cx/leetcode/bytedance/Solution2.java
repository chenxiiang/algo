package cx.leetcode.bytedance;

public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            node.next = new ListNode(sum);
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            node = node.next;
        }
        if (carry != 0) {
            node.next = new ListNode(carry);
        }
        return head.next;
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
