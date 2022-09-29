package cx.leetcode.dp.rob;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有房屋形成一棵二叉树，直接相连的会报警，也就是不能同时偷相邻两层的节点
 */
public class Solution337 {
    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        //从爷爷一直考虑到孙子
        int do_it = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right)) +
                (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        int not_do = rob(root.left) + rob(root.right);
        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }

    /**
     * 每个节点可以考虑偷和不偷两种状态
     * 如果当前节点选择偷，则两个子节点都不能偷，则当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     * 当前节点选择不偷，那么子节点偷不偷都行，只要以这个子节点为root的子树能拿出最多的钱就行，当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     * int[] res = new int[2] 0 代表不偷，1 代表偷
     * root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) + Math.max(rob(root.right)[0], rob(root.right)[1])
     * root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
     */
    int rob1(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数
    */
    int[] dp(TreeNode root) {
        if (root == null) {
//            偷不偷都是0
            return new int[]{0, 0};
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int rob = root.val + left[0] + right[0];
        int no_rob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{no_rob, rob};
    }

    static class TreeNode {
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
