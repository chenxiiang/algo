package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution216 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;
    int k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        backtrack(1, n);
        return res;
    }

    void backtrack(int start, int target) {
        if (trackSum == target && track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            trackSum += i;
            track.add(i);
            backtrack(i + 1, target);
            trackSum -= i;
            track.removeLast();
        }
    }
}
