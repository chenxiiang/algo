package cx.leetcode.bytedance;

public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode x = findKFromEnd(dummy, n + 1);
        x.next = x.next.next;
        return dummy.next;
    }

    private ListNode findKFromEnd(ListNode head, int k) {
        ListNode p1, p2;
        p1 = p2 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p2;
    }
}
