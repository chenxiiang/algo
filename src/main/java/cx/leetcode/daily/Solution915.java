package  cx.leetcode.daily;

/**
 * 数组分成左右两部分，左边的都小于右边所有的
 * 维护两个变量 当前最大值和左数组最大值
 */

public class Solution915 {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int leftMax = nums[0], leftPos = 0, currMax = nums[0];
        for (int i = 1; i < n - 1; i++) {
            //0-i位置中的最大元素
            currMax = Math.max(currMax, nums[i]);
            if (nums[i] < leftMax) {
                leftPos = i;
                leftMax = currMax;
            }
        }
        return leftPos + 1;
    }
}
