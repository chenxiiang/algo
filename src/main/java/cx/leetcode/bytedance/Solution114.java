package cx.leetcode.bytedance;

public class Solution114 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        //先把左右子树都拉平，然后分别记录起来
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;

        //然后把左子树放到右子树的位置
        root.left = null;
        root.right = left;

        //再把之前的右子树接到左子树下面
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
}
