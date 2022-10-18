package cx.leetcode.bytedance;

public class Solution143 {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = findMid(head);
        //右半边的头结点
        ListNode l2 = reverseList(mid.next);
        mid.next = null;
        //l2长度一定是大于等于l1
        while (l2 != null) {
            ListNode temp = l2.next;
            l2.next = head.next;
            head.next = l2;
            head = l2.next;
            l2 = temp;
        }
    }

    public ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    class ListNode {
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
