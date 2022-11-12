package cx.leetcode.bytedance;

public class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        ListNode others = head.next.next;
        second.next = first;
        first.next = swapPairs(others);
        return second;
    }
}
