package cx.leetcode.bytedance;

public class Solution99 {
    TreeNode first = null, second = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        inorderTraverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left);
        //中序遍历找到不在正确位置的两个节点
        if (root.val < prev.val) {
            //因为中序遍历的结果是一个非递减的序列，如果root比前面的节点小，说明root不符合有序性
            if (first == null) {
                //prev是比较大的元素，一定不能放在前面，所以prev是第一个错位的节点
                first = prev;
            }
            //root是第二个错位的节点
            second = root;
        }
        prev = root;
        inorderTraverse(root.right);
    }
}
