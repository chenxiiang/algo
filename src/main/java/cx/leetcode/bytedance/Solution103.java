package cx.leetcode.bytedance;

import java.util.*;

public class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        boolean fromLeft = false;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Deque<Integer> valList = new ArrayDeque<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (fromLeft) {
                    valList.offerFirst(curr.val);
                } else {
                    valList.offerLast(curr.val);
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            res.add(new ArrayList<>(valList));
            fromLeft = !fromLeft;
        }
        return res;
    }

}
