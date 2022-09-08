package cx.leetcode.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution652 {
    //记录所有子树以及出现的次数
    Map<String, Integer> subTrees = new HashMap<>();

    //记录重复的子树根节点
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return res;
    }

    private String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        String myself = left + "," + right + "," + root.val;
        int freq = subTrees.getOrDefault(myself, 0);
        //==1，也就是要求第二次出现的时候添加一次，就算出现过多次也只添加一次，不能是大于1的数
        if (freq == 1) {
            res.add(root);
        }
        subTrees.put(myself, freq + 1);
        return myself;
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
