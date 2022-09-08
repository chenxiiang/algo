package cx.leetcode.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

public class Solution297 {
    String NULL = "#";

    String SEP = ",";

    StringBuilder sb = new StringBuilder();

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            sb.append(NULL).append(SEP);
        } else {
            sb.append(root.val).append(SEP);
            serialize(root.left);
            serialize(root.right);
        }
        return sb.toString();
    }
    // private void serialize(TreeNode root)

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return deserialize(nodes);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
