package cx.leetcode.bytedance;

public class Solution70 {
    public int climbStairs(int n) {
        // if (n <= 2) {
        //     return n;
        // }
        memo = new int[n + 1];
        // int[] dp = new int[n + 1];
        // dp[0] = 0;
        // dp[1] = 1;
        // dp[2] = 2;
        // for (int i = 3; i <= n; i++) {
        //     dp[i] = dp[i - 1] + dp[i - 2];
        // }
        // return dp[n];
        return helper(n);
    }

    int[] memo;

    private int helper(int n) {
        if (n <= 2) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(n - 1) + helper(n - 2);
        return memo[n];
    }
}
