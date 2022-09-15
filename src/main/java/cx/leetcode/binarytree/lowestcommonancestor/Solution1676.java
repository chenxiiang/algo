package cx.leetcode.binarytree.lowestcommonancestor;

import java.util.HashSet;
import java.util.Set;

public class Solution1676 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> vals = new HashSet<>();
        for (TreeNode node : nodes) {
            vals.add(node.val);
        }
        return find(root, vals);
    }

    private TreeNode find(TreeNode root, Set<Integer> vals) {
        if (root == null) {
            return null;
        }
        if (vals.contains(root.val)) {
            return root;
        }
        TreeNode left = find(root.left, vals);
        TreeNode right = find(root.right, vals);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
