package cx.leetcode.binarytree.lowestcommonancestor;

/**
 * 结构中有parent，类似求链表交点
 */
public class Solution1652 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }

            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
        }
        return a;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
