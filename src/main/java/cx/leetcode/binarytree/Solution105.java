package cx.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

//根据前中遍历还原二叉树
public class Solution105 {
    Map<Integer, Integer> valToIdx = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //用于寻找root在中序数组中的位置，只有全部元素不重复时才用map，否则还是只能用数组遍历
        for (int i = 0; i < inorder.length; i++) {
            valToIdx.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /*前序遍历和中序遍历构造二叉树
    build 函数的定义：
    若前序遍历数组为 preorder[preStart..preEnd]，
    中序遍历数组为 inorder[inStart..inEnd]，
    构造二叉树，返回该二叉树的根节点
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        //这里的判断，两个数组都可以，主要就是start和end的关系
        if (preStart > preEnd) {
            return null;
        }
        //前序遍历数组的第一个元素就是root节点的值
        int rootVal = preorder[preStart];
        //寻找root在中序遍历数组中位置
        int rootIndex = valToIdx.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int leftSize = rootIndex - inStart;
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);
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
