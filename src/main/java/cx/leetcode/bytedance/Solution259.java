package cx.leetcode.bytedance;

import java.util.Arrays;

public class Solution259 {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            res += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return res;
    }

    private int twoSumSmaller(int[] nums, int start, int target) {
        int low = start, high = nums.length - 1;
        int count = 0;
        while (low < high) {
            if (nums[low] + nums[high] < target) {
                // nums[lo] 和 nums[lo+1..hi]
                // 中的任一元素之和都小于 target
                count += high - low;
                low++;
            } else {
                high--;
            }
        }
        return count;
    }
}
