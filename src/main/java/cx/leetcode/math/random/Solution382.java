package cx.leetcode.math.random;

import java.util.Random;

public class Solution382 {
    ListNode head;

    public Solution382(ListNode head) {
        this.head = head;
    }

    /**
     * 假设总共有 n 个元素，我们要的随机性无非就是每个元素被选择的概率都是 1/n
     * 第 i 个元素被选择的概率是 1/i，在第 i+1 次不被替换的概率是 1 - 1/(i+1)，在第 i+2 次不被替换的概率是 1 - 1/(i+2)
     * 以此类推，相乘的结果是第 i 个元素最终被选中的概率，也就是 1/n。因此，该算法的逻辑是正确的。
     */
    public int getRandom() {
        Random random = new Random();
        int i = 0, res = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            // 生成一个 [0, i) 之间的整数
            // 这个整数等于 0 的概率就是 1/i
            if (0 == random.nextInt(i)) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }

    /**
     * 如果要在单链表中随机选择 k 个数，只要在第 i 个元素处以 k/i 的概率选择该元素，以 1 - k/i 的概率保持原有选择即可
     *
     * @param head
     * @param k
     * @return
     */
    int[] getRandom(ListNode head, int k) {
        Random random = new Random();
        int[] res = new int[k];
        ListNode p = head;
        //前k个先默认选上
        for (int i = 0; i < k && p != null; i++) {
            res[i] = p.val;
            p = p.next;
        }

        int i = k;
        while (p != null) {
            i++;
            // 生成一个 [0, i) 之间的整数
            int j = random.nextInt(i);
            // 这个整数小于 k 的概率就是 k/i
            if (j < k) {
                res[j] = p.val;
            }
            p = p.next;
        }
        return res;
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
