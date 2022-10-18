package cx.leetcode.bytedance;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode headNode = new ListNode(), p = headNode;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
        Arrays.stream(lists).forEach(head -> {
            if (head != null) {
                pq.offer(head);
            }
        });
        while (!pq.isEmpty()) {
            //最小堆里面拿最小值
            ListNode node = pq.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        return headNode.next;
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
