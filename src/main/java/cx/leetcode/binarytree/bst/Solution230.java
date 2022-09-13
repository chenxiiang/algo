package cx.leetcode.binarytree.bst;

public class Solution230 {
    int rank = 0, ans;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return ans;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        rank++;
        if (rank == k) {
            ans = root.val;
        }
        traverse(root.right, k);
    }
}
