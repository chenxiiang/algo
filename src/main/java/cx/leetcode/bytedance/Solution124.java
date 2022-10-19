package cx.leetcode.bytedance;

public class Solution124 {
    /**
     * 重点！！
     * 最大路径和不是说从根节点到叶子节点中最大的和，而是从任何一个节点都可以开始，左根右，或者左根根，或者右根根
     * 以下例子使用root,root.left=a,root.right=null,a.left==b,a.right=c来说明
     * 最大和可能是root,a,b,c,b-a,c-a,b-a-c,b-a-root,c-a-root
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return maxSum;
    }

    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //左右子树的和，如果是负数，就直接用0
        int left = Math.max(maxSum(root.left), 0);
        int right = Math.max(maxSum(root.right), 0);
        //节点的最大路径和取决于该节点的值与该节点的左右子节点的最大值
        int newMax = root.val + left + right;
        //这里得到的是每个节点的，或者说是类似这种情况下的结果:b-a-c
        maxSum = Math.max(maxSum, newMax);
        //这里是递归向上时返回的结果，如果要向上计算，那么这条路径肯定不能把左右都加上，只能是b-a-root,c-a-root这样的
        return root.val + Math.max(left, right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
