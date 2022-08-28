package cx.leetcode.linkedlist;

public class Solution234 {
    //后序遍历
//    public boolean isPalindrome(ListNode head) {
//        left = head;
//        return traverse(head);
//    }
//
//    ListNode left;
//
//    private boolean traverse(ListNode right) {
//        if (right == null) {
//            return true;
//        }
//        boolean res = traverse(right.next);
//        res = res && (right.val == left.val);
//        left = left.next;
//        return res;
//    }

    // 反转后半链表
    //后序遍历
    // public boolean isPalindrome(ListNode head) {
    //     left = head;
    //     return traverse(head);
    // }
    //
    // ListNode left;
    //
    // private boolean traverse(ListNode right) {
    //     if (right == null) {
    //         return true;
    //     }
    //     boolean res = traverse(right.next);
    //     res = res && (right.val == left.val);
    //     left = left.next;
    //     return res;
    // }

    //反转后半部分
    public boolean isPalindrome(ListNode head) {
        //先找中点
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // if (fast != null) {
        //     //链表长度奇数，再向前一步
        //     slow = slow.next;
        // }
        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
