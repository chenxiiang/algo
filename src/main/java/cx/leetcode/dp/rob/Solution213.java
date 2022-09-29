package cx.leetcode.dp.rob;

import java.util.Arrays;

/**
 * 打家劫舍2
 * 房屋环状相连，也就是说不能同时偷第一家和最后一家（两个都不偷的情况不考虑）
 * 只要在0..n-2和1..n-1中找个最大值就行
 */
public class Solution213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        /**
         * copyOfRange参数区间是{from，to）
         */
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, n - 1)), myRob(Arrays.copyOfRange(nums, 1, n)));
    }

    public int myRob(int[] nums) {
        int n = nums.length;
        // int[] dp = new int[n + 1];
        // //一家都不抢的时候是0
        // dp[0] = 0;
        // dp[1] = nums[0];
        int dp_0 = 0, dp_1 = nums[0];
        for (int i = 2; i <= n; i++) {
            //f(k)=max{f(k−1),nums[k-1]+f(k−2)}
            int curr = Math.max(dp_1, dp_0 + nums[i - 1]);
            dp_0 = dp_1;
            dp_1 = curr;
        }
        return dp_1;
    }
}
