package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.List;

public class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return build(1, n);
    }

    public List<TreeNode> build(int low, int high) {
        List<TreeNode> res = new ArrayList<>();
        if (low > high) {
            res.add(null);
            return res;
        }
        //这里遍历的i作为root
        for (int i = low; i <= high; i++) {
            //递归求所有可能的左右子树
            List<TreeNode> leftTrees = build(low, i - 1);
            List<TreeNode> rightTrees = build(i + 1, high);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
