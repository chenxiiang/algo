package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution46 {
    /**
     * 全排列
     * 无重复不可复选
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
    // 结束条件：nums 中的元素全都在 track 中出现
    private void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            //用于控制长度，比如要求的不是全排列，而是长度为k的
            res.add(new LinkedList<>(track));
            //长度相等时，for循环也已经完成，有没有return无所谓
        }
        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (used[i]) {
                // nums[i]已经在track中，跳过
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

