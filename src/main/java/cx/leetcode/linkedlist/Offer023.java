package cx.leetcode.linkedlist;

/**
 * 和160题是一样的，这是剑指offer023
 */
public class Offer023 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            if (p1 != null) {
                p1 = p1.next;
            } else {
                p1 = headB;
            }
            if (p2 != null) {
                p2 = p2.next;
            } else {
                p2 = headA;
            }
        }
        return p1;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
