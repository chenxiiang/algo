package cx.leetcode.dp.rob;

/**
 * 打家劫舍1
 */
public class Solution198 {
    public int rob(int[] nums) {
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
