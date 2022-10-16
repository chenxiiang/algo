package cx.leetcode.bytedance;

public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                //要求不足k个不用反转
                return head;
            }
            //这里b其实指向的是第k+1个
            b = b.next;
        }
        //所以reverse方法反转的区间是[a,b)
        ListNode newHead = reverse(a, b);
        //a已经是当前这组k个节点的最后一个，继续下一组k个节点
        a.next = reverseKGroup(b, k);
        return newHead;
    }


    private ListNode reverse(ListNode a, ListNode b) {
        ListNode prev = null, curr = a, next;
        while (curr != b) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //反转的区间是[a,b)
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
