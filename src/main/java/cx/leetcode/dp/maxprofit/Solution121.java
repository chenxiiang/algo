package cx.leetcode.dp.maxprofit;

public class Solution121 {
    /**
     * 根据188题中写的框架，交易次数只有一次
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     * = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     */
    public int maxProfit(int[] prices) {
//用框架做
        int n = prices.length;
        //第二维只有持有和未持有两种状态,1表示持有，0表示不持有
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                //dp[0][0]，第0天还没开始交易，如果未持有，则利润是0
                dp[i][0] = 0;
                // 根据状态转移方程可得：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //这里的-prices[i]，解释就是昨天不持有今天买入了，本来还有个运算
//            但是这道题交易次数是1，也就是昨天啥都没有，今天才开始交易，利润就是0-今天价格
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }
    
    //空间复杂度优化,避免了大量的数组赋值，实际运行时间也提升很多
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_0 = 0, dp_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_1 = Math.max(dp_1, -prices[i]);
        }
        return dp_0;
    }

    /*
    只针对这一题，不用股票问题的框架
    一次交易要利润最大化，也就是求最值，在最低点买入，在最高点卖出
     */
    public int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            //每天都考虑下今天的价格是不是历史最低，如果不是历史最低，那么减去历史最低能不能获得最高利润
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
