package cx.leetcode.dp.maxprofit;

public class Solution309 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                //base case 1
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if (i - 2 == -1) {
                //base case 2
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    //    优化空间复杂度
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int dp_0 = 0, dp_1 = Integer.MIN_VALUE;
        int dp_pre = 0;   // 代表dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, dp_pre - prices[i]);
            dp_pre = temp;
        }
        return dp_0;
    }
}
