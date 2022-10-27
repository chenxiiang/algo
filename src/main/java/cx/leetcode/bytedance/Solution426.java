package cx.leetcode.bytedance;

public class Solution426 {
    /**
     * 想把整棵 BST 变成环形链表，可以先把左右子树变成环形链表，然后把 root.val 接在中间，这样就形成了整棵 BST 的环形链表。
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node leftHead = treeToDoublyList(root.left);
        Node rightHead = treeToDoublyList(root.right);
        Node leftTail, rightTail;
        if (leftHead != null) {
            leftTail = leftHead.left;
            root.left = leftTail;
            leftTail.right = root;
        } else {
            //搜索树，如果左边头为空，那就是没有左子树，左边的头尾都是root了，就是叶子节点了
            leftTail = leftHead = root;
        }

        if (rightHead != null) {
            rightTail = rightHead.left;
            root.right = rightHead;
            rightHead.left = root;
        } else {
            rightHead = rightTail = root;
        }
        leftHead.left = rightTail;
        rightTail.right = leftHead;
        return leftHead;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
