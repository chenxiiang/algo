package cx.leetcode.bytedance.pathsum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution113 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        traverse(root, targetSum);
        return res;
    }

    private void traverse(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        int remain = target - root.val;
        if (root.left == null && root.right == null) {
            if (remain == 0) {
                track.addLast(root.val);
                res.add(new LinkedList<>(track));
                track.removeLast();
            }
            return;
        }

        track.addLast(root.val);
        traverse(root.left, remain);
        track.removeLast();

        track.addLast(root.val);
        traverse(root.right, remain);
        track.removeLast();
    }
}
