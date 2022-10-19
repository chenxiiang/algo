package cx.leetcode.bytedance;

public class ReverseList {
    public ListNode reverseList(ListNode head) {
        //如果head就是个空链表，直接返回null
        //如果到达最后一个节点，则该节点就是反转后的头结点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
