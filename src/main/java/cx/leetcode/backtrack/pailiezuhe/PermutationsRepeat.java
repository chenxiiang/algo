package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationsRepeat {
    /**
     * 无重复可复选 排列
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> permuteRepeat(int[] nums) {
        backtrack(nums);
        return res;
    }

    public void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        //每次都从0开始
        for (int i = 0; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums);
            track.removeLast();
        }
    }
}
