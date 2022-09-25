package cx.leetcode.dp;

public class Solution53 {
    //不能用滑动窗口，因为数组中的数字可以是负数
    // 滑动窗口算法无非就是双指针形成的窗口扫描整个数组/子串，但关键是要清楚地知道什么时候应该移动右侧指针来扩大窗口，什么时候移动左侧指针来减小窗口。
    // 而对于这道题目，当窗口扩大的时候可能遇到负数，窗口中的值也就可能增加也可能减少，
    // 这种情况下不知道什么时机去收缩左侧窗口，也就无法求出「最大子数组和」。
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }
}
