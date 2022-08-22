package cx.leetcode.linkedlist;

public class Solution141 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //如果有环，循环一定会到这里，但是注意，相遇的位置只能确定是在环里，但是不一定是环的起点，所以142不能直接用这个解法
            if (slow == fast) {
                return true;
            }
        }
        //如果无环，则一定会有null
        return false;
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
