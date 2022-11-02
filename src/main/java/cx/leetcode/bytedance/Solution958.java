package cx.leetcode.bytedance;

import java.util.LinkedList;
import java.util.Queue;

public class Solution958 {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        boolean isEnd = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    isEnd = true;
                } else {
                    if (isEnd) {
                        return false;
                    } else {
                        queue.offer(curr.left);
                        queue.offer(curr.right);
                    }
                }
            }
        }
        return true;
    }
}
