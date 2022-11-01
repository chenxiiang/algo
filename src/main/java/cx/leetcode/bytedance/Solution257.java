package cx.leetcode.bytedance;

public class Solution257 {
    List<String> res = new ArrayList<>();

    LinkedList<String> path = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path.addLast(root.val + "");
            res.add(String.join("->", path));
            path.removeLast();
            return;
        }
        path.add(root.val + "");
        traverse(root.left);
        traverse(root.right);
        path.removeLast();
    }
}
