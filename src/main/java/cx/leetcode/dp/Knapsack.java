package cx.leetcode.dp;

public class Knapsack {
    public int knapsack(int bagSize, int[] weight, int[] val) {
        int n = weight.length;
        int[][] dp = new int[n + 1][bagSize + 1];
        //说明base case，背包容量为0时所有物品的价值都只能是0
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= bagSize; w++) {
                if (w - weight[i - 1] < 0) {
                    // 这种情况下只能选择不装入背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装入或者不装入背包，择优
                    dp[i][w] = Math.max(dp[i - 1][w - weight[i - 1]] + val[i - 1], dp[i - 1][w]);
                }
            }
        }
        return dp[n][bagSize];
    }
}
