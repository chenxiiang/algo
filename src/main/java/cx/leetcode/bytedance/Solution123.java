package cx.leetcode.bytedance;

public class Solution123 {
    //最多两次交易，k最大为2
    public int maxProfit(int[] prices) {
        int n = prices.length, maxK = 2;
        int[][][] dp = new int[n][maxK + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][maxK][0];
    }

    // 状态转移方程：
// dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
// dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
// dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
// dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
//    public int maxProfit(int[] prices) {
//        // base case
//        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
//        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
//        for (int price : prices) {
//            dp_i20 = Math.max(dp_i20, dp_i21 + price);
//            dp_i21 = Math.max(dp_i21, dp_i10 - price);
//            dp_i10 = Math.max(dp_i10, dp_i11 + price);
//            dp_i11 = Math.max(dp_i11, -price);
//        }
//        return dp_i20;
//    }
}
