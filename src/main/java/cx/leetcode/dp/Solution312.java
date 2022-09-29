package cx.leetcode.dp;

public class Solution312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }
        // base case
        int[][] dp = new int[n + 2][n + 2];
        // dp[x][x]=0，这是base case，结果就是dp[0][n+1]，所以遍历顺序要么斜着遍历或者从下到上从左到右遍历，选择第二种
        //    i应该从下往上
        for (int i = n; i >= 0; i--) {
            //j从左往右
            for (int j = i + 1; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[j] * points[k]);
                }
            }
        }
        return dp[0][n + 1];
    }
}
