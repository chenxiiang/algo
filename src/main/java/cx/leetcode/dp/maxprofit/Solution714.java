package cx.leetcode.dp.maxprofit;

public class Solution714 {
    //    不限交易次数，收取手续费
//    只要在不限交易次数的基础上进行变化
//    卖出的时候减去手续费（卖出的时候减可能会溢出），或者在买入的时候减去手续费（相当于买入股票的价格升高了）
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
//                base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }

    //优化空间复杂度
    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int dp_0 = 0, dp_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, temp - prices[i] - fee);
        }
        return dp_0;
    }
}
