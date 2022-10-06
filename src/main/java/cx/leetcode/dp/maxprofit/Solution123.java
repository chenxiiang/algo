package cx.leetcode.dp.maxprofit;

public class Solution123 {
    //    最多两笔交易
//    原始的状态转移方程，没有可化简的地方
//dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
//dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

    //   这里无法像k=1或者无穷时消除k的影响，所以必须对k进行穷举
//    // 原始版本
//int maxProfit_k_2(int[] prices) {
//    int max_k = 2, n = prices.length;
//    int[][][] dp = new int[n][max_k + 1][2];
//    for (int i = 0; i < n; i++) {
//        for (int k = max_k; k >= 1; k--) {
//            if (i - 1 == -1) {
//                // 处理 base case
//                dp[i][k][0] = 0;
//                dp[i][k][1] = -prices[i];
//                continue;
//            }
//            dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
//            dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
//        }
//    }
//    // 穷举了 n × max_k × 2 个状态，正确。
//    return dp[n - 1][max_k][0];
//}
//    k从最大开始穷举是因为初始状态是第0天开始，没有进行过交易，所以最大交易次数应该是max_k，随着状态推进，k减小
    public int maxProfit(int[] prices) {
//k的取值范围比较小，可以直接穷举
// 状态转移方程：
// dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
// dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
// dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
// dp[i][1][1] = max(dp[i-1][1][1], -prices[i])

// 空间复杂度优化版本
        // base case
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }
}
