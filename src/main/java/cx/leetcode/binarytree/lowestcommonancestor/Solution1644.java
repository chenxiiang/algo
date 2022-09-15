package cx.leetcode.binarytree.lowestcommonancestor;

public class Solution1644 {
    boolean foundP = false, foundQ = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find(root, p.val, q.val);
        if (!foundP || !foundQ) {
            return null;
        }
        return res;
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);
        if (left != null && right != null) {
            return root;
        }
        //题目说了要找的两个节点不一定存在，所以不能遇到一个目标值就返回
        //所以在后序位置，在遍历所有节点的过程中看下是否存在这两个值，进行标记
        if (root.val == val1 || root.val == val2) {
            //这就是root是其中一个要找的，在子树中找到了另一个
            if (root.val == val1) {
                foundP = true;
            } else {
                foundQ = true;
            }
            return root;
        }
        //root不是目标节点，看看哪个子树是
        return left == null ? right : left;
    }
}
