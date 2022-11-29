package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution40 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return res;
    }

    private void backtrack(int[] candidates, int start, int target) {
        if (trackSum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            trackSum += candidates[i];
            track.add(candidates[i]);

            backtrack(candidates, i + 1, target);

            trackSum -= candidates[i];
            track.removeLast();
        }
    }
}
