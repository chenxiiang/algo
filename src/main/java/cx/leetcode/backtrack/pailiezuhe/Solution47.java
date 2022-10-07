package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution47 {
    /**
     * 相比46题，有重复元素
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                //因为数组中有重复元素，要去掉重复元素，就要保证相同的元素在排列中的相对位置保持不变
                //在元素相同的情况下，如果前面的没有用过，则跳过
                //或者排序后直接用一个变量记录上一个值，如果当前值跟上一个一样，直接跳过
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
