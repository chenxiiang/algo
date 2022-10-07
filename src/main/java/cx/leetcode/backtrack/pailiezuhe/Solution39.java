package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution39 {
    /**
     * 数组无重复，求目标和的子集，可重复选取
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        backtrack(candidates, 0, target);
        return res;
    }

    public void backtrack(int[] nums, int start, int target) {
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }

        //超过目标和，停止向下遍历
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            trackSum += nums[i];
            track.add(nums[i]);

            //可重复选取的关键就是重复遍历i，而不是遍历下一个元素
            backtrack(nums, i, target);

            trackSum -= nums[i];
            track.removeLast();
        }
    }
}
