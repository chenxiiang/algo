package cx.leetcode.bytedance;

public class Solution221 {
    public int maximalSquare(char[][] matrix) {
        /**
         * min(上，左，左上)+1
         * dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长, 则递推式为:
         * dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
         **/
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
