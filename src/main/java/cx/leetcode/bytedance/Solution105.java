package cx.leetcode.bytedance;

import java.util.HashMap;
import java.util.Map;

public class Solution105 {
    //用于寻找当前节点值在中序数组中的位置，全部元素不重复可以用map，否则只能用数组
    Map<Integer, Integer> val2Idx = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            val2Idx.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        //前序遍历的第一个元素就是根节点
        int rootVal = preorder[preStart];
        int rootIdx = val2Idx.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int leftSize = rootIdx - inStart;
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIdx - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIdx + 1, inEnd);
        return root;
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
