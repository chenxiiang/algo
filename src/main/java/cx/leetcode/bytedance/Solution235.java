package cx.leetcode.bytedance;

public class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val1 = Math.min(p.val, q.val);
        int val2 = Math.max(p.val, q.val);
        //保证参数中val1是较小的，val2是较大额
        return find(root, val1, val2);
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        if (root.val > val2) {
            return find(root.left, val1, val2);
        } else if (root.val < val1) {
            return find(root.right, val1, val2);
        } else {
            return root;
        }
    }
}
