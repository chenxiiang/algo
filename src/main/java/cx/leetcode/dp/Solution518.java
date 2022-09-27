package cx.leetcode.dp;

public class Solution518 {
    public int change(int amount, int[] coins) {
        // dp[i] = dp[i]+dp[j-coins[i]]
        // 本题的双层遍历对顺序是有要求的，必须先遍历物品，再遍历背包
        // 因为是零钱问题，求得是组合数，先遍历物品，就只有一种顺序
        // 如果先遍历背包再遍历物品，背包容量的每一个值都经历了多个值的计算，就会得到多种顺序，变成了排列数
        int n = coins.length;
        int[] dp = new int[amount + 1];
        //base case:背包容量（金额总数）为0时，只有一种情况
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    //二维dp
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    //这里把当前物品加入的情况用的是dp[i][j - coins[i - 1]] 而不是dp[i-1][j - coins[i - 1]]
                    //因为每个物品是无限数量的，就算这次装入了还是可以继续使用的
                    //这恰恰就是01背包和完全背包的区别
                    // dp[i][j] = dp[i-coins[j-1]][j] +dp[i][j-1] #完全背包，可以重复使用自己
                    // dp[i][j] = dp[i-coins[j-1]][j-1] +dp[i][j-1] #01背包，不能重复使用
                    //同时，反映到一维数组的方法下，就是内层遍历不用再倒序遍历了，因为是同一层的数可以无限用，不存在被下一层覆盖的问题
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }
}
