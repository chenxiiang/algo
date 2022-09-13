package cx.leetcode.binarytree.bst;

public class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                //如果左右子节点都不为空，为了保持BST的性质，必须找到左子树中最大的节点或者右子树中最小的节点来代替自己，这里是第二种方式
                //先找到右子树中最小的节点
                TreeNode minNode = getMin(root.right);
                //删除右子树中最小的节点
                root.right = deleteNode(root.right, minNode.val);
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
            }
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        //获取以node为根节点的子树上的最小节点
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
