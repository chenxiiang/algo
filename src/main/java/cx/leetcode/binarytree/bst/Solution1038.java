package cx.leetcode.binarytree.bst;

public class Solution1038 {
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private int sum;

    private void traverse(TreeNode root) {
        //对于BST来说，中序遍历是升序序列，但是这道题需要从大到小，所以可以反向中序
        if (root == null) {
            return;
        }
        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
