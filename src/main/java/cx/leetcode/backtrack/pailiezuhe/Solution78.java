package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 * 元素无重复不可复选
 */
public class Solution78 {
    List<List<Integer>> res = new ArrayList<>();
    //记录回溯的递归路径
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    public void backtrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
