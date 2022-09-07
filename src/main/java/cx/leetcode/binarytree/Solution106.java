package cx.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

//根据中后遍历还原二叉树
public class Solution106 {
    Map<Integer, Integer> valToIdx = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIdx.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        //这里的判断，两个数组都可以，主要就是start和end的关系
        if (postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int rootIdx = valToIdx.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int leftSize = rootIdx - inStart;
        root.left = build(inorder, inStart, rootIdx - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorder, rootIdx + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
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
