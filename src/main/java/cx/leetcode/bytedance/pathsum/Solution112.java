package cx.leetcode.bytedance.pathsum;

public class Solution112 {
    //分解子问题
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //遍历二叉树
    int target;
    boolean found = false;
    // 记录遍历过程中的路径和
    int curSum = 0;

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        this.target = targetSum;
        traverse(root);
        return found;
    }

    // 二叉树遍历函数
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历位置
        curSum += root.val;
        if (root.left == null && root.right == null) {
            if (curSum == target) {
                found = true;
            }
        }

        traverse(root.left);
        traverse(root.right);

        // 后序遍历位置
        curSum -= root.val;
    }
}
