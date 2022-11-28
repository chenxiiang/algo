package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution39 {
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

    private void backtrack(int[] nums, int start, int target) {
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            trackSum += nums[i];
            track.add(nums[i]);
            //可重复选取，继续从i开始
            backtrack(nums, i, target);
            trackSum -= nums[i];
            track.removeLast();
        }
    }
}
