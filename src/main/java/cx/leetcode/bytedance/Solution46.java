package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution46 {
    /**
     * 回溯就是一个记录结果的，一个记录中间路径的，用于回溯
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            //一条路走到头了就添加进结果集
            res.add(new LinkedList<>(track));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }
}
