package cx.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

//根据前后遍历二叉树，这种情况下的二叉树不止一个，返回任意一个
//为什么不唯一？
//可以直接把前序数组的第二个元素当做左子树的根，然后尝试还原
public class Solution889 {
    Map<Integer, Integer> valToIdx = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            valToIdx.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        //越界
        if (preStart > preEnd) {
            return null;
        }
        //前序遍历只有一个元素，说明到叶子节点
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        int rootVal = preorder[preStart];
        // root.left 的值是前序遍历第二个元素
        // 通过前序和后序遍历构造二叉树的关键在于通过左子树的根节点
        // 确定 preorder 和 postorder 中左右子树的元素区间
        //只是假设左子树的根节点是前序数组的第二个元素，但是实际上左子树可能是空的，那么这个元素就应该是右子树的根节点
        int leftRootVal = preorder[preStart + 1];
        int leftRootIdx = valToIdx.get(leftRootVal);
        int leftSize = leftRootIdx - postStart + 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize, postorder, postStart, leftRootIdx);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, postorder, leftRootIdx + 1, postEnd - 1);
        return root;
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
